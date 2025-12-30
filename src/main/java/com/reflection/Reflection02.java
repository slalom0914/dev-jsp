package com.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class Reflection02 {
  public static void main(String[] args) {
    Class<?> clazz = null;
    try {
      clazz = Class.forName("com.mvc.DeptController");
      System.out.println("클래스 로드 성공: " + clazz.getName());
    } catch (ClassNotFoundException e) {
      System.err.println("클래스를 찾을 수 없습니다: " + e.getMessage());
      e.printStackTrace();
      return;
    }
    
    Object controller = null;
    try {
      controller = clazz.getDeclaredConstructor().newInstance();
      System.out.println("인스턴스 생성 성공: " + controller.getClass().getName());
    } catch (Exception e) {
      System.err.println("인스턴스 생성 실패: " + e.getMessage());
      e.printStackTrace();
      return;
    }
    
    // 모든 메서드 출력 (서블릿 클래스 로드 시도하지 않도록 주의)
    System.out.println("\n=== 선언된 메서드 목록 ===");
    Method[] methods = null;
    try {
      methods = clazz.getDeclaredMethods();
    } catch (NoClassDefFoundError e) {
      System.err.println("일부 클래스를 로드할 수 없습니다: " + e.getMessage());
      System.err.println("서블릿 API가 클래스패스에 없을 수 있습니다.");
      System.err.println("대신 인터페이스의 메서드 정보를 확인합니다.");
      // 인터페이스의 메서드 정보 확인
      Class<?>[] interfaces = clazz.getInterfaces();
      for(Class<?> iface : interfaces) {
        System.out.println("\n인터페이스: " + iface.getName());
        try {
          Method[] ifaceMethods = iface.getDeclaredMethods();
          for(Method m : ifaceMethods) {
            System.out.print("  메서드: " + m.getName() + "(");
            Parameter[] params = m.getParameters();
            for(int i = 0; i < params.length; i++) {
              System.out.print(params[i].getType().getSimpleName());
              if(i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
          }
        } catch (NoClassDefFoundError ex) {
          System.err.println("  인터페이스 메서드 로드 실패: " + ex.getMessage());
        }
      }
      return;
    }
    
    for(Method method : methods) {
        System.out.print("메서드: " + method.getName() + "(");
        Parameter[] params = method.getParameters();
        for(int i = 0; i < params.length; i++) {
            System.out.print(params[i].getType().getSimpleName() + " " + params[i].getName());
            if(i < params.length - 1) System.out.print(", ");
        }
        System.out.println(")");
    }
    
    // 모든 필드 출력
    System.out.println("\n=== 선언된 필드 목록 ===");
    Field[] fields = clazz.getDeclaredFields();
    for(Field field : fields) {
        System.out.println("필드: " + field.getType().getSimpleName() + " " + field.getName());
    }
    
    // 특정 메서드 찾기 (메서드 이름으로만 검색)
    System.out.println("\n=== deptList 메서드 찾기 ===");
    Method method = null;
    if(methods != null) {
      for(Method m : methods) {
        if("deptList".equals(m.getName()) && m.getParameterCount() == 2) {
          method = m;
          System.out.println("메서드 찾기 성공: " + method.getName());
          try {
            System.out.println("반환 타입: " + method.getReturnType().getSimpleName());
            Parameter[] params = method.getParameters();
            System.out.print("파라미터 타입: ");
            for(int i = 0; i < params.length; i++) {
              System.out.print(params[i].getType().getName());
              if(i < params.length - 1) System.out.print(", ");
            }
            System.out.println();
          } catch (NoClassDefFoundError e) {
            System.err.println("파라미터 타입을 확인할 수 없습니다 (서블릿 API 없음)");
          }
          break;
        }
      }
      
      if(method != null) {
        try {
          method.setAccessible(true);
          System.out.println("메서드 접근 가능하도록 설정 완료");
        } catch (SecurityException e) {
          System.err.println("보안 예외 발생: " + e.getMessage());
          e.printStackTrace();
        }
      } else {
        System.out.println("deptList 메서드를 찾을 수 없습니다.");
      }
    }
    
    System.out.println("\n=== 리플렉션 테스트 완료 ===");
  }
}
