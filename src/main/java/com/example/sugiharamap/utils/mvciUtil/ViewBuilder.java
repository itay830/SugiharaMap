package com.example.sugiharamap.utils.mvciUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.sugiharamap.utils.nodeUtil.NodeInitializer;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public abstract class ViewBuilder implements Builder<Region> {
    public static List<Method> initialize(Class<?> obj) {
        List<Method> nodeInitMethods = new ArrayList<>();
        for (Method declaredMethod : obj.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(NodeInitializer.class)) {
                int index = 0;
                int order = ((NodeInitializer) declaredMethod.getAnnotations()[0]).order();
                while (index < nodeInitMethods.size() &&
                        ((NodeInitializer) nodeInitMethods.get(index).getAnnotations()[0])
                                .order() < order)
                {
                    index++;
                }
                nodeInitMethods.add(index, declaredMethod);
            }
        }
        return nodeInitMethods;
    }

    protected abstract void initViews();
}