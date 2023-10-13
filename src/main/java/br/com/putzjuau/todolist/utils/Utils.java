package br.com.putzjuau.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));

    }

    // PEGA TUDO DE PROPRIEDADE NULA
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);// uma forma de acessar os objetos dentro do java

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // valores nulos
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }

        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result); // converte o numero de nomes das propriedades para uma array
    }
}
