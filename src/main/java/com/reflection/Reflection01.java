package com.reflection;

import java.lang.reflect.Method;

public class Reflection01 {

	public static void main(String[] args) {
	//리플렉션 API를 활용하여 클래스 인스턴스 생성하기
		//클래스 로드
		try {
			Class<?> sonataClass = Class.forName("com.reflection.Sonata");
			Object sonata = sonataClass.getDeclaredConstructor().newInstance(); 
			//start메서드 찾기
			Method startMethod = sonataClass.getMethod("start");
			System.out.println(startMethod.getName());
			startMethod.invoke(sonata);
		} catch (ClassNotFoundException e) {
			System.out.println("Sonata 클래스 찾기 실패");
		} catch (NoSuchMethodException ne) {
			System.out.println("메서드를 찾을 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
