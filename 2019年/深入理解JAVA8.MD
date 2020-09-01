# 深入理解 JAVA 8

## 1. 匿名内部类

## 2. Lambda

## 3. Method Reference 方法推导(方法引用)

## 4. Stream

#### 4.1 测试for、lambda、stream、parallel stream 的效率

```
package pers.east.learning.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  @author East.F
 *
 *  测试用例描述 ： 准备 1 个亿的 dish 数据， 从中取出 卡路里 低于400 的水果，并进行排序
 *  1. 传统 for if 实现
 *  2. lambda 实现
 *  2. stream 实现
 *  3. parallel Stream 实现
 *
 * 测试结果： 测试数据1个亿
 * test list size : 100000000
 * ==================java7 for and if ====================
 * 【for and if】 data size: 30000000
 * 【for and if】 cost time : 18809
 * ==================java8 lambda====================
 * 【for and if】 data size: 30000000
 * 【for and if】 cost time : 13498
 * ==================java 8 stream====================
 * 【java 8 stream】 data size: 30000000
 * 【java 8 stream】 cost time : 4685
 * ==================java 8 parallel Stream====================
 * 【java 8 parallel stream】 data size: 30000000
 * 【java 8 parallel stream】 cost time : 3685
 *
 *
 * 结论：
 * 当数据量到达一定量的时候， 使用stream 或者并行的 stream 会快一点，
 * 数据量很少的时候 还是 for 更快。
 *
 * 并行化：因为并行并一定意味着性能会更好。 应该是缺少一个不字（不一定性能会更好）。
 * stream 在代码上更优雅。
 */
public class StreamDemo {
    public static void main(String[] args) {
        testByForIf();
        testByLambda();
        testByStream();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testByParallelStream();
    }


    private static List<Dish> menu;
    static {
        List<Dish> model = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish("salmon", false, 470, Dish.Type.FISH));

        menu = new ArrayList<>();
        for(int i = 0;i< 10000000 ;i++){
            menu.addAll(model);
        }
        System.out.println("test list size : "+menu.size());
    }


    private static void testByForIf(){
        System.out.println("==================java7 for and if ====================");
        long start1 = System.currentTimeMillis();
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: menu){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        System.out.println("【for and if】 data size: "+lowCaloricDishesName.size());
        System.out.println("【for and if】 cost time : "+(System.currentTimeMillis()-start1));
    }

    public static void testByLambda(){
        System.out.println("==================java8 lambda====================");
        long start2 = System.currentTimeMillis();
        List<Dish> lowCaloricDishes2 = new ArrayList<>();
        for(Dish d: menu){
            if(d.getCalories() < 400){
                lowCaloricDishes2.add(d);
            }
        }
        Collections.sort(lowCaloricDishes2,Comparator.comparing(Dish::getCalories));
        List<String> lowCaloricDishesName2 = new ArrayList<>();
        for(Dish d: lowCaloricDishes2){
            lowCaloricDishesName2.add(d.getName());
        }
        System.out.println("【for and if】 data size: "+lowCaloricDishesName2.size());
        System.out.println("【for and if】 cost time : "+(System.currentTimeMillis()-start2));
    }

    public static void testByStream(){
        System.out.println("==================java 8 stream====================");
        long start3 = System.currentTimeMillis();
        List<String> lowCaloricDishesName3= menu.stream()
                .filter(d ->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("【java 8 stream】 data size: "+lowCaloricDishesName3.size());
        System.out.println("【java 8 stream】 cost time : "+(System.currentTimeMillis()-start3));
    }

    public static void testByParallelStream(){
        System.out.println("==================java 8 parallel Stream====================");
        long start4 = System.currentTimeMillis();
        List<String> lowCaloricDishesName4= menu.parallelStream()
                .filter(d ->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("【java 8 parallel stream】 data size: "+lowCaloricDishesName4.size());
        System.out.println("【java 8 parallel stream】 cost time : "+(System.currentTimeMillis()-start4));
    }

}

```

利用jconsole查看stream和parallel stream的区别

![并行流](https://raw.github.com/eastFu/docs/master/learning-notes-img/2019/parallelstream.png)

> 1. 测试用例描述 ： 准备 1 个亿的 dish 数据， 从中取出 卡路里 低于400 的水果，并进行排序
 *  传统 for if 实现
 *  lambda 实现
 *  stream 实现
 *  parallel Stream 实现

>2. 测试结果： 测试数据1个亿
 * test list size : 100000000
 * ==================java7 for and if ====================
 * 【for and if】 data size: 30000000
 * 【for and if】 cost time : 18809
 *  ==================java8 lambda====================
 * 【for and if】 data size: 30000000
 * 【for and if】 cost time : 13498
 * ==================java 8 stream====================
 * 【java 8 stream】 data size: 30000000
 * 【java 8 stream】 cost time : 4685
 * ==================java 8 parallel Stream====================
 * 【java 8 parallel stream】 data size: 30000000
 * 【java 8 parallel stream】 cost time : 3685

>3. 结论：
* 当数据量到达一定量的时候， 使用stream 或者并行的 stream 会快一点，数据量很少的时候 还是for 更快。
* 并行化：因为并行并一定意味着性能会更好。 应该是缺少一个不字（不一定性能会更好）。stream 在代码上更优雅。

#### 4.2 find

#### 4.3 map

#### 4.4 filter

#### 4.5 reduce

#### 4.6 match

## 5 Optional

> Optional 构造有三种 : of(T value) 、 empty() 、  ofNullable(T value)


* get()是这些方法中最简单但又最不安全的方法。如果变量存在，它直接返回封装的变量
值，否则就抛出一个NoSuchElementException异常。所以，除非你非常确定Optional
变量一定包含值，否则使用这个方法是个相当糟糕的主意。此外，这种方式即便相对于
嵌套式的null检查，也并未体现出多大的改进。
* orElse(T other)是我们在代码清单10-5中使用的方法，正如之前提到的，它允许你在
Optional对象不包含值时提供一个默认值。
* orElseGet(Supplier<? extends T> other)是orElse方法的延迟调用版，Supplier
方法只有在Optional对象不含值时才执行调用。如果创建默认值是件耗时费力的工作，
你应该考虑采用这种方式（借此提升程序的性能），或者你需要非常确定某个方法仅在
Optional为空时才进行调用，也可以考虑该方式（这种情况有严格的限制条件）。
* orElseThrow(Supplier<? extends X> exceptionSupplier)和get方法非常类似，
它们遭遇Optional对象为空时都会抛出一个异常，但是使用orElseThrow你可以定制希
望抛出的异常类型。
* ifPresent(Consumer<? super T>)让你能在变量值存在时执行一个作为参数传入的
方法，否则就不进行任何操作

## 6 Collector
先上一个案例，Collector 的牛B之处一目了然
```
package pers.east.learning.collector;

import pers.east.learning.methodreference.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author East.F
 * @ClassName: CollectorIntroduce
 * @Description: TODO
 * @date 2019/7/21 10:15
 */
public class CollectorIntroduce {
    private static List<Apple> list;

    public static void main(String[] args) {
        list = Arrays.asList(new Apple(150, "red", "usa")
                , new Apple(120, "red", "china")
                , new Apple(160, "pink", "japan")
                , new Apple(110, "yellow", "korea")
                , new Apple(170, "green", "russia")
                , new Apple(100, "green", "england"));

        List<Apple> greenList = list.stream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);

        System.out.println("==============================");
        Optional.ofNullable(groupAppleByNormal()).ifPresent(System.out::println);

        System.out.println("==============================");
        Optional.ofNullable(groupAppleByFunction()).ifPresent(System.out::println);

        System.out.println("==============================");
        Optional.ofNullable(groupAppleByCollector()).ifPresent(System.out::println);
    }

    //传统方法的分组实现
    private static Map<String, List<Apple>> groupAppleByNormal() {
        Map<String, List<Apple>> groupData = new HashMap<>();
        for (Apple apple : list) {
            if (!groupData.keySet().contains(apple.getColor())) {
                List<Apple> colorList = new ArrayList<>();
                colorList.add(apple);
                groupData.put(apple.getColor(), colorList);
            } else {
                groupData.get(apple.getColor()).add(apple);
            }
        }
        return groupData;
    }

    // 函数调用的分组实现
    private static Map<String, List<Apple>> groupAppleByFunction() {
        Map<String, List<Apple>> groupData = new HashMap<>();
        list.stream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(groupData.get(a.getColor())).orElseGet(()->{
                List<Apple> list = new ArrayList<>();
                groupData.put(a.getColor(),list);
                return list;
            });
            colorList.add(a);
        });
        return groupData;
    }

    // 使用 java 8 collector 的分组实现
    private static Map<String, List<Apple>> groupAppleByCollector() {
        return list.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}

```

### 6.1 collector源码分析
```
/**
 * T 是接收stream()得到的类型
 * A 是一个累加器,寄存器，容器 accumulator
 * R 是返回值
*/
public interface Collector<T, A, R> {

    Supplier<A> supplier();

    BiConsumer<A, T> accumulator();

    BinaryOperator<A> combiner();

    Function<A, R> finisher();

	Set<Characteristics> characteristics();

	public static<T, R> Collector<T, R, R> of(Supplier<R> supplier,
                                              BiConsumer<R, T> accumulator,
                                              BinaryOperator<R> combiner,
                                              Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = (characteristics.length == 0)
                                  ? Collectors.CH_ID
                                  : Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH,
                                                                           characteristics));
        return new Collectors.CollectorImpl<>(supplier, accumulator, combiner, cs);
    }


    public static<T, A, R> Collector<T, A, R> of(Supplier<A> supplier,
                                                 BiConsumer<A, T> accumulator,
                                                 BinaryOperator<A> combiner,
                                                 Function<A, R> finisher,
                                                 Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(finisher);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = Collectors.CH_NOID;
        if (characteristics.length > 0) {
            cs = EnumSet.noneOf(Characteristics.class);
            Collections.addAll(cs, characteristics);
            cs = Collections.unmodifiableSet(cs);
        }
        return new Collectors.CollectorImpl<>(supplier, accumulator, combiner, finisher, cs);
    }

	enum Characteristics {
        CONCURRENT,
        UNORDERED,
        IDENTITY_FINISH
    }
}
```
# 6.2 Future

## 6.2.1 Completable Future

>核心方法：
* supplyAsync:
* runAsync:
* =================
* thenApply:
* thenApplyAsyn:
* handle:
* =================
* whenComplete:
* =================
* thenRun:
* thenAccept:
* =================
* thenCompose:
* thenCombine:
* thenAcceptBoth:
* =================
* applyToEither:其中一个执行完后，接受结果运行，并返回值
* acceptEither:其中任意一个执行完后，接受结果并处理，但是不返回值
* runAfterEither:其中任意一个执行完后，不接受结果，不返回值
* runAfterBoth:两个CompletableFuture都执行结束后运行
* anyOf:一组CompletableFuture 任意一个执行过后运行
* allOf:一组CompletableFuture等到全部completableFuture 都执行完毕



![](https://raw.github.com/eastFu/docs/master/learning-notes-img/2019/completable-future.png)
# 7. New Date API

## 7.1 LocalDate
## 7.2 LocalTime
## 7.3 LocalDateTime
## 7.4 Instant
## 7.5 Duration
## 7.6 Period
## 7.7 Date Formatter
## 7.8 Parse Date

# 8. Java8 Functional Programming
