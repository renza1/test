package jdk8;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
//		Integer a = null;
//		int name = Optional.ofNullable(a).map(Integer::intValue).orElse(1);
//		System.out.println(name);
//
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.forEach(s ->System.out.println(Optional.ofNullable(s).filter(u->u> 2).orElse(-1)));
//		list.forEach(s ->System.out.println(Optional.ofNullable(s).filter(u->{
//			return u>2;
//		})));

		   //创建Optional实例，也可以通过方法返回值得到。
	    Optional<String> name = Optional.of("Sanaulla");
	 
	    //创建没有值的Optional实例，例如值为'null'
	    Optional<String> empty = Optional.ofNullable(null);
	 
	    //isPresent方法用来检查Optional实例是否有值。
	    if (name.isPresent()) {
	      //调用get()返回Optional值。
	      System.out.println(name.get());
	    }
	    
	    empty.ifPresent((value) -> {
	        System.out.println("The length of the value is: " + value);
	      });
	}

}
