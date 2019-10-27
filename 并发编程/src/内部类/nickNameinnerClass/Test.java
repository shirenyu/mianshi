package 内部类.nickNameinnerClass;

public class Test {
    public static void main(String[] args) {
        //1、普通类，无继承、非抽象类、非接口,只能实现该类中已经声明的方法，为声明在内部中实现会找不到。
        Apple apple = new Apple(){
            void run(){
                System.out.println("普通类吃苹果");
            }
        };
        apple.run();
        // 2、抽象类
        AbsApple absApple = new AbsApple() {
            @Override
            void run() {
                System.out.println("抽象类吃苹果");
            }
        };

        // 3、继承类的子类
        AppleChild appleChild = new AppleChild(){
            void test(){
                System.out.println("继承类吃苹果");
            }
            void run(){
                System.out.println("导出类吃苹果");
            }
        };
        appleChild.run();

        /**
         * 总结，无论是普通类、导出类、接口、抽象类只能在匿名内部类中重写已经声明的方法，也就是只能在匿名写导出类的方法。
         */
    }
}
