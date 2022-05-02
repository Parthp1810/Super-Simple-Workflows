package com.example.module;

import com.example.annotations.Item;
import com.example.annotations.StockPile;
import com.example.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.VisibleForTesting;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StockPileModule extends AbstractModule {

    @Override
    protected void configure() {
        final StockPileInterceptor stockPileInterceptor = new StockPileInterceptor();
        requestInjection(stockPileInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(StockPile.class), stockPileInterceptor);
    }

    @VisibleForTesting
    public static class StockPileInterceptor implements MethodInterceptor {

        @VisibleForTesting
        @Inject
        protected StorageService storageService;

        @VisibleForTesting
        @Inject
        protected ObjectMapper objectMapper;

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            Object response;

            final Optional<Annotation> optionalAnnotation = Stream.of(methodInvocation.getMethod().getDeclaredAnnotations())
                    .filter(annotation -> annotation instanceof StockPile)
                    .findFirst();

            if (optionalAnnotation.isPresent()) {
                List<Object> requests = new ArrayList<>();
                final String methodName = methodInvocation.getMethod().getName();
                final Annotation[][] parameterAnnotations = methodInvocation.getMethod().getParameterAnnotations();

                for (int index = 0; index < parameterAnnotations.length; ++index) {
                    for (final Annotation annotation : parameterAnnotations[index]) {
                        if (annotation instanceof Item) {
                            requests.add(methodInvocation.getArguments()[index]);
                        }
                    }
                }
                try {
                    response = methodInvocation.proceed();
                    storageService.save(requests, response, methodName);
                } catch (Exception e) {
                    storageService.save(requests, e, methodName);
                    throw e;
                }
                return response;
            }
            return methodInvocation.proceed();
        }
    }
}
