package com.learn.springboot.practice.utils.diff;

import cn.hutool.core.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author lfq
 */
@Slf4j
public class ObjectDiffUtil {

    /**
     * 比较对象相同属性值的差异，返回差异
     *
     * @param source 原始对象
     * @param target 目标对象
     * @return 差异列表
     */
    public static List<Differance> getObjDiffDifferance(Object source, Object target) {
        Map<String, DiffPropertyWrapper> sourcePropertyMap = new HashMap<>();
        Map<String, DiffPropertyWrapper> targetPropertyMap = new HashMap<>();
        sourcePropertyMap = convertObject2Map("", source, sourcePropertyMap);
        targetPropertyMap = convertObject2Map("", target, targetPropertyMap);
        return compare(sourcePropertyMap, targetPropertyMap);
    }

    /**
     * 对象差异比较
     *
     * @param sourcePropertyMap 原始对象属性map
     * @param targetPropertyMap 目标对象属性map
     * @return
     */
    private static List<Differance> compare(Map<String, DiffPropertyWrapper> sourcePropertyMap,
                                            Map<String, DiffPropertyWrapper> targetPropertyMap) {
        List<Differance> differanceList = new ArrayList<>();
        Set<String> sourceKeySet = sourcePropertyMap.keySet();
        Set<String> keySet = new HashSet<>(sourceKeySet);
        Set<String> targetKeySet = targetPropertyMap.keySet();
        //统一获取原始&目标属性key
        keySet.addAll(targetKeySet);
        if (CollectionUtils.isNotEmpty(keySet)) {
            keySet.forEach(key -> {
                DiffPropertyWrapper source = sourcePropertyMap.get(key);
                DiffPropertyWrapper target = targetPropertyMap.get(key);
                Differance differance = buildDifferance(source, target);
                if (differance.getNewValue() == null && differance.getOldValue() != null) {
                    differance.setDiffType(DiffTyeEnum.DELETE.name());
                    differanceList.add(differance);
                }
                if (differance.getNewValue() != null && differance.getOldValue() == null) {
                    differance.setDiffType(DiffTyeEnum.ADD.name());
                    differanceList.add(differance);
                }
                if (differance.getNewValue() != null && differance.getOldValue() != null
                        && !differance.getNewValue().equals(differance.getOldValue())) {
                    differance.setDiffType(DiffTyeEnum.UPDATE.name());
                    differanceList.add(differance);
                }
            });
        }
        return differanceList;
    }

    /**
     * 封装差异
     *
     * @param source 原始属性
     * @param target 目标属性
     * @return
     */
    private static Differance buildDifferance(DiffPropertyWrapper source, DiffPropertyWrapper target) {
        Differance differance = new Differance();
        if (source == null && target != null) {
            differance.setFieldName(target.getName());
            differance.setName(target.getNameCn());
            differance.setOldValue(null);
            differance.setNewValue(target.getValue());
        }
        if (source != null && target == null) {
            differance.setFieldName(source.getName());
            differance.setName(source.getNameCn());
            differance.setOldValue(source.getValue());
            differance.setNewValue(null);
        }
        if (source != null && target != null) {
            differance.setFieldName(source.getName());
            differance.setName(source.getNameCn());
            differance.setOldValue(source.getValue());
            differance.setNewValue(target.getValue());
        }
        return differance;
    }


    /**
     * 递归获取每个属性的值
     *
     * @param path        属性相对位置
     * @param source      原始对象
     * @param sourceKvMap 转换后结果map
     * @return
     */
    private static Map<String, DiffPropertyWrapper> convertObject2Map(String path, Object source, Map<String, DiffPropertyWrapper> sourceKvMap) {
        if (source != null) {
            //反射获取所有属性
            Field[] fields = getAllFields(source);
            //获取对象类名
            String className = source.getClass().getSimpleName();
            if ("".equals(path)) {
                path = className;
            }
            String prePath = path;
            try {
                for (Field field : fields) {
                    //获取属性类型
                    Class<?> type = field.getType();
                    String name = field.getName();
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(DiffProperty.class)) {
                        DiffProperty property = field.getAnnotation(DiffProperty.class);
                        //基本类型直接放入map
                        if (ClassUtil.isSimpleValueType(type)) {
                            path = prePath + "." + name;
                            Object value = field.get(source);
                            if (value != null) {
                                DiffPropertyWrapper wrapper = new DiffPropertyWrapper(path, property.name(), property.desc(), value);
                                sourceKvMap.put(path, wrapper);
                            }
                        }
                        //集合类型
                        else if (Collection.class.isAssignableFrom(type)) {
                            List<?> genericList = (List<?>) field.get(source);
                            if (CollectionUtils.isNotEmpty(genericList)) {
                                //获取集合类型元素的唯一属性
                                Field keyField = getKeyField(field);
                                path = prePath + "#" + name;
                                if (keyField != null) {
                                    keyField.setAccessible(true);
                                    //遍历每个元素递归提取各个属性的值
                                    for (Object obj : genericList) {
                                        //获取每个唯一key的值
                                        Class<?> keyType = keyField.getType();
                                        if (Collection.class.isAssignableFrom(keyType)) {
                                            List<?> keyList = (List<?>) keyField.get(source);
                                            if (CollectionUtils.isNotEmpty(keyList)) {
                                                Object key = keyList.get(0);
                                                convertObject2Map(path + "[" + key + "]", obj, sourceKvMap);
                                            }
                                        } else {
                                            Object key = keyField.get(obj);
                                            convertObject2Map(path + "[" + key + "]", obj, sourceKvMap);
                                        }
                                    }
                                }
                            }
                        }
                        //Map类型
                        else if (Map.class.isAssignableFrom(type)) {
                            Map<?, ?> genericMap = (Map<?, ?>) field.get(source);
                            if (genericMap != null && !genericMap.isEmpty()) {
                                path = prePath + "#" + name;
                                for (Map.Entry<?, ?> entry : genericMap.entrySet()) {
                                    String key = (String) entry.getKey();
                                    Object value = entry.getValue();
                                    convertObject2Map(path + "[" + key + "]", value, sourceKvMap);
                                }
                            }
                        } else {
                            path = prePath + "#" + name;
                            Object obj = field.get(source);
                            convertObject2Map(path, obj, sourceKvMap);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                log.error("convertObject2Map error", e);
            }
        }
        return sourceKvMap;
    }

    /**
     * 获取类所有属性【包括父类】
     *
     * @param object
     * @return
     */
    private static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 获取唯一属性
     *
     * @param field
     * @return
     */
    private static Field getKeyField(Field field) {
        Class<?> genericClass = null;
        //获取集合对象类型
        if (field.getGenericType() instanceof ParameterizedType) {
            genericClass = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        }
        if (genericClass == null) {
            return null;
        }
        //反射获取集合对象类型所有属性
        Field[] collFields = genericClass.getDeclaredFields();
        //唯一键对应字段
        Field keyField;
        for (int j = 0; j < collFields.length; j++) {
            if (collFields[j].isAnnotationPresent(DiffUniqueKey.class)) {
                keyField = collFields[j];
                DiffUniqueKey keyFieldAnnotation = keyField.getAnnotation(DiffUniqueKey.class);
                if (keyFieldAnnotation != null) {
                    return keyField;
                }
                break;
            }
        }
        return field;
    }
}
