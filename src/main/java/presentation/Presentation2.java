package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.*;
import java.lang.*;
import java.lang.reflect.*;


public class Presentation2 {
    public static void main(String[] args){
        try{
            String [] classNames = loadClassNames("F:\\DevIT\\Education\\2.Emsi\\2027.5thYear_Sem1\\6.JEE\\TPS\\MicroServices\\tp1\\src\\main\\resources\\config.txt");
            // dao
            IDao dao = (IDao) instantiateClass(classNames[0]);
            System.out.println("[INFO] DAO instance created: " + dao.getClass().getName());
            // metier
            IMetier metier = (IMetier) instantiateClass(classNames[1]);
            System.out.println("[INFO] Metier instance created: " + metier.getClass().getName());
            // Inject dependencies
            injectDependencies(metier, "setDao" ,dao);
            // Executer
            double result = metier.calcul();
            System.out.println("[INFO] Result: " + result);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    private static String[] loadClassNames(String fileName) throws IOException {
        String[] classNames = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 2; i++) {
                classNames[i] = reader.readLine().trim();
            }
        }
        return classNames;
    }

    private static Object instantiateClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        return constructor.newInstance();
    }

    private static void injectDependencies(Object target, String setterName, Object dependency) throws Exception {
        Class<?> targetClass = target.getClass();
        Method setterMethod = targetClass.getMethod(setterName, dependency.getClass().getInterfaces()[0]);
        setterMethod.invoke(target, dependency);
    }
}
