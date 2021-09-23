package java8;

import com.beust.jcommander.internal.Lists;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName StreamP
 * @Description Stream 记录
 * @Author pandacode
 * @Date 2021/9/19 上午10:14
 * @Version 1.0
 **/
public class StreamP {

    /**
     * 初始化
     */
    @Test
    public void initStream() {
        // array转stream
        int[] num = {1};
        IntStream arrayStream = Arrays.stream(num);

        // 自定义赋值转stream
        Stream<Integer> integerStream = Stream.of(2, 2);

        // 集合转stream
        List<Integer> list = Lists.newArrayList(3, 3, 3);
        Stream<Integer> collectionStream = list.stream();

        // 无限连续的Stream，可用于生成常量流和随机元素流。因为是无限，一般都会设定limit。
        Stream<Integer> generateStream = Stream.generate(() -> 4).limit(4);

        // 迭代器生成
        Stream<Integer> iterateStream = Stream.iterate(5, a -> a).limit(5);

        // Stream builder 构造器生成
        Stream<Object> buildStream = Stream.builder().add(1).build();

        // Stream empty 返回一个空的顺序Stream
        Stream<Object> emptyStream = Stream.empty();
    }

    /**
     * api 简单使用
     */
    @Test
    public void streamApi() {
        // 普通结构 [1,2,3]
        List<Integer> datas = Lists.newArrayList(1, 2, 2, 3);

        // forEach 循环，console: 1 2 2 3
        datas.stream().forEach(a -> System.out.println(a));

        // forEachOrdered 循环，确保顺序执行(使用并行流时forEach顺序会打乱)，console: 1 2 2 3
        datas.stream().forEachOrdered(a -> System.out.println(a));

        // filter 过滤，console: 2 2
        datas.stream().filter(a -> a == 2).forEach(a -> System.out.println(a));

        // map/mapToInt/mapToLong/mapToDouble 构造数据 返回类型Object，console: 5a 10a 10a 15a
        datas.stream().map(a -> (a * 5) + "a").forEach(a -> System.out.println(a));

        // distinct 去重 console: 1 2 3
        datas.stream().distinct().forEach(a -> System.out.println(a));

        // sorted 排序 console: 1 2 2 3
        datas.stream().sorted(Comparator.naturalOrder()).forEach(a -> System.out.println(a));

        // peek 主要为了支持调试 console: 1 2 2 3 2 2
        datas.stream().peek(a -> System.out.println(a)).filter(a -> a == 2)
                .forEach(a -> System.out.println(a));

        // limit 只取几位 console: 1 2
        datas.stream().limit(2).forEach(a -> System.out.println(a));

        // skip 跳过几位 console: 2 2 3
        datas.stream().skip(1).forEach(a -> System.out.println(a));

        // toArray 转换成array
        Object[] objects = datas.stream().toArray();

        // reduce 聚合求值 console: 8
        Integer integer = datas.stream().reduce((a, b) -> a + b).get();

        // collect 使用Collector对此流的元素执行可变归约操作
        Set<Integer> collect = datas.stream().collect(Collectors.toSet());

        // min 最小值
        Integer minNum = datas.stream().min(Comparator.naturalOrder()).get();

        // max 最大值
        Integer maxNum = datas.stream().max(Comparator.naturalOrder()).get();

        // count 数据数量
        long count = datas.stream().count();

        // anyMatch 判断的条件里，任意一个元素成功，返回true console: true
        boolean anyMatch = datas.stream().anyMatch(Predicate.isEqual(1));

        // allMatch 判断条件里的元素，所有的都是，返回true console: false
        boolean allMatch = datas.stream().allMatch(Predicate.isEqual(2));

        // noneMatch 与allMatch相反，判断条件里的元素，所有的都不是，返回true console: false
        boolean noneMatch = datas.stream().noneMatch(Predicate.isEqual(3));

        // findFirst 获取第一个元素
        Integer findFirst = datas.stream().findFirst().get();

        // findAny 获取随机一个元素
        Integer findAny = datas.stream().findAny().get();

        // --------------------------------------分割线---------------------------------------

        // 多级结构 [[1,2],[3,4],[5,6]]
        List<int[]> datas2
                = Lists.newArrayList(new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6});

        // flatMap/flatMapToInt/flatMapToLong/flatMapToDouble 数据扁平化 console: 1 2 3 4 5 6
        datas2.stream().flatMapToInt(a -> Arrays.stream(a)).forEach(a -> System.out.println(a));


    }

    @Test
    public void test() {
        Stream<Object> build = Stream.builder().add(1).build();
        build.forEach(a-> System.out.println(a));
    }

}
