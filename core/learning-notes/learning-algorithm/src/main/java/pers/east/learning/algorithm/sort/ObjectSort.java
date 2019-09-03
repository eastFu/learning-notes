package pers.east.learning.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 对象排序： 按照对象里的字段值进行任意排序算法的排序
 * @author eastFu
 */
public class ObjectSort {

    public static void sort(User[] users){
        for (int i = 1; i < users.length; i++) {
            for (int j = i; j > 0; j--) {
                if (users[j].getUserId() < users[j-1].getUserId()) {
                    swap(users,j,j-1);
                }else{
                    break;
                }
            }
        }
    }
    public static void swap(User[] ar, int aIndex, int bIndex) {
        User temp = ar[aIndex];
        ar[aIndex] = ar[bIndex];
        ar[bIndex] = temp;
    }

    public static void main(String[] args) {
        User[] users = {new User(5,"lao li",15),new User(8,"lao liu",19)
                        ,new User(6,"lao fu",12),new User(4,"lao wang",18)
                        ,new User(9,"lao ji",14)};
        sort(users);
        System.out.println(JSON.toJSONString(users));
    }
}

class User{
    private int userId;
    private String userName;
    private int age;

    public User(int userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
