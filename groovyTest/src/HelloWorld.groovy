/**
 * Created by xiaojun.zhang on 2015/8/27.
 */
class HelloWorld {
    static void main(def args){
//        def va = """
//                   1.姓名
//                   2.出生
//                   3.性别
//                   4.年龄
//                """
//        print va

        //fun("alice",4)
/*        fun1("lisi",3)
        fun1("lisi")
        fun1("zhangsan",null)*/

//        stringTest()
//        listTest()
        listTest1()
    }

    private static listTest(){
        def myArr = 25..10
        def myBrr = 10..25
        println(myArr)
        //println myBrr
        println("the type is " + myArr.class)
        println("the first num is " + myArr[0])
        println("the last num is " + myArr[-1])
        println("sub arr: " + myArr[2,5])
        println("arr reverse: "+ myArr.reverse())
        println("remove spcified element"+ (myArr - myArr[1]))
        println("remove spcified collection"+ (myArr - [myArr[1],myArr[2],myArr[3]]))

    }

    private static listTest1(){
        def coll = ["C","C++","JAVA","JS","GROOVY","PYTHON"]
        println("the type is " + coll.class)  //ArrayList
        println("the collection is: "+coll)   //coll.add("c#")
        coll << "C#"
        println(coll)

        println(coll*.toLowerCase())          //coll* 遍历处理coll中的元素

    }

    private static def stringTest() {
        def var = fun2(country)
        println(var)
        println("length: " + var.length())
        println("country is " + var[0, 2])
        println("country is " + var.substring(0, 2))
    }

    static def fun(name,age){
        print("your name is: "+ name+", and age is: "+age)
    }

    static def fun1(name,age=4){//当只传入一个name的时候（仅一个如参），这里的age会默认为4，
        if (age==null){
            println("name is "+ name+", and age is unknown!")
        }else{
            println("name is "+ name+", and age is "+age)
        }
    }


    static def country = "南非"

    static def fun2(country){
        return "${country}世界杯"
    }


}
