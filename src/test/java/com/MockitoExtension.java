package com;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Parameter;

import static org.mockito.Mockito.mock;
import static org.mockito.internal.util.MockUtil.getMockName;

public class MockitoExtension implements TestInstancePostProcessor, ParameterResolver {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) throws Exception {

        MockitoAnnotations.initMocks(testInstance);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(Mock.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getMock(parameterContext.getParameter(), extensionContext);
    }

    private Object getMock(Parameter parameter, ExtensionContext extensionContext) {
        Class<?> mockType = parameter.getType();
        Store mocks = extensionContext.getStore(Namespace.create(MockitoExtension.class, mockType));

        String mockName = String.valueOf(getMockName(parameter));


        if (mockName != null) {
            return mocks.getOrComputeIfAbsent(mockName,
                    key -> mock(mockType, mockName));
        } else {
            return mocks.getOrComputeIfAbsent(mockType.getCanonicalName(),
                    key -> mock(mockType));
        }
    }
}
