package com.example.teamlearningproxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyExample {

    public static void main(String[] args) {
        Repository repositoryProxy = getRepository();

        repositoryProxy.save("Sample object");
        repositoryProxy.findById(123);
        System.out.println(repositoryProxy);
    }

    interface Repository {
        void save(Object obj);

        Object findById(int id);
    }

    static class RepositoryInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("proxying: " + proxy.getClass().getName());
            System.out.println("method execution: " + method.getName());
            if (args != null) {
                System.out.println("method arguments: " + Arrays.stream(args).map(Object::toString).toList());
            }
            return null;
        }
    }

    private static Repository getRepository() {
        return (Repository) Proxy.newProxyInstance(
                Repository.class.getClassLoader(),
                new Class[]{Repository.class},
                new RepositoryInvocationHandler()
        );
    }


}
