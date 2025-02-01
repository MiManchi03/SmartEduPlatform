import java.util.ArrayList;
import java.util.Scanner;

public class StudentTest {
    private static final int ADD_A_STUDENT = 1;//添加学生
    private static final int DELETE_A_STUDENT = 2;//删除学生
    private static final int MODIFYING_STUDENT = 3;//修改学生
    private static final int INQUIRE_ABOUT_STUDENTS = 4;//查询学生
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();
        for (int i1 = 0; i1 < 1; ) {//此处循环是用来让用户选择是否退出系统
            System.out.println("登录成功，是否使用学生管理系统");
            System.out.println("是请按 “11”，否请按“22”");
            int number = sc.nextInt();
            if (number == 11) {

                if (i1 == 0) {
                    System.out.println("************欢迎来到学生管理系统************");
                }

                System.out.println("添加学生请按 “1”");
                System.out.println("删除学生请按 “2”");
                System.out.println("修改学生请按 “3”");
                System.out.println("查询学生请按 “4”");
                System.out.println("请输入对应数字:");
                int num = sc.nextInt();
                //1.添加信息模块
                if (num == ADD_A_STUDENT) {

                    System.out.println("开始添加学生信息，请确保ID唯一");

                    for (int i = 0; i < 1; ) {//这个循环是让用户可以自行决定是否继续添加学生信息，
                        // 如果“是”就可以一直添加信息，如果“不是”就停止添加信息
                        Student s = new Student();
                        System.out.println("请输入学生ID:");
                        String id = sc.next();

                        for (int i4 = 0; i4 < 1; ) {//这个循环是判断录入的ID是否重复，如果重复就一直循环到不重复为止

                            if (soleId(id, list)) {
                                System.out.println("该ID已存在，请重新输入学生ID:");
                                id = sc.next();
                            } else {
                                i4++;
                            }

                        }

                        System.out.println("请输入学生名字:");
                        String name = sc.next();
                        System.out.println("请输入学生年龄:");
                        String age = sc.next();
                        System.out.println("请输入学生家庭地址:");
                        String home = sc.next();
                        s.setId(id);
                        s.setName(name);
                        s.setAge(age);
                        s.setHome(home);
                        list.add(s);
                        System.out.println("是否继续添加(是请按1，否请按2):");
                        int eng = sc.nextInt();
                        if (eng == 1) {
                        } else if (eng == 2) {
                            System.out.println("已将全部学生信息录入系统");
                            i++;//此处返回到菜单
                        } else {

                            for (int k = 0; k < 1; ) {
                                System.out.println("输入错误，请正确输入1或者2:");
                                eng = sc.nextInt();

                                if (eng == 2) {
                                    k++;
                                    i++;//此处返回到菜单
                                } else if (eng == 1) {
                                    k++;
                                }

                            }

                        }
                    }
                    //2.删除信息模块
                } else if (num == DELETE_A_STUDENT) {//这个循环为了让用户自行选择是否继续删除，
                    // 如果“是”就可以一直删除信息，如果“不是”就停止删除信息
                    int num4 = 1;//开启删除功能区域的“钥匙”
                    for (int i6 = 0; i6 < 1; ) {
                        if (num4 == 1) {
                            if (list.size() == 0) {
                                System.out.println("系统中还未添加学生信息，请先添加:");
                                i6++;
                            } else {
                                System.out.println("请输入要删除学生的ID:");
                                String id = sc.next();
                                if (indexes(id, list) >= 0) {
                                    list.remove(indexes(id, list));
                                    System.out.println("删除成功");
                                    System.out.println("是否继续删除，是请按“1”，否请按“2”");
                                    num4 = sc.nextInt();
                                } else {
                                    System.out.println("该学生不存在");//此处返回到菜单
                                }
                            }
                        } else if (num4 == 2) {
                            i6++;
                        } else {
                            System.out.println("输入错误，是否继续删除(是请按1，否请按2):");
                            num4 = sc.nextInt();
                        }
                    }

                    //3.修改信息模块
                } else if (num == 3) {
                    int num2 = 1;
                    for (int i2 = 0; i2 < 1; ) {//这个循环是让用户可以修改完一个学生后，自行选择是否继续修改下个

                        if (list.size() == 0) {
                            System.out.println("系统中还未添加学生信息，请先添加:");
                            i2++;
                        } else {

                            System.out.println("请输入要修改学生的ID:");
                            String id = sc.next();

                            if (num2 == 1) {
                                if (indexes(id, list) >= 0) {
                                    for (int i3 = 0; i3 < 1; ) {//这个循环是防止num1出现1,2,3,4以外的数字，
                                        // 如果出现就行一直重复下去直到输入正确
                                        System.out.println("修改ID请按 “1”");
                                        System.out.println("修改名字请按 “2”");
                                        System.out.println("修改年龄请按 “3”");
                                        System.out.println("修改家庭地址请按 “4”");
                                        int num1 = sc.nextInt();

                                        if (num1 == 1) {//修改ID

                                            System.out.println("请输入学生新ID:" + "(原ID:" + list.get(indexes(id, list)).getId() + ")");
                                            String newId = sc.next();

                                            for (int i7 = 0; i7 < 1; ) {//这个循环是判断录入的ID是否重复，如果重复就一直循环到不重复为止

                                                if (soleNewId(newId, list)) {
                                                    System.out.println("该ID已存在，请重新输入学生ID:");
                                                    newId = sc.next();
                                                } else {
                                                    i7++;
                                                }

                                            }

                                            list.get(indexes(id, list)).setId(newId);
                                            System.out.println("修改成功");
                                            System.out.println("是否继续修改(是请按1，否请按2):");
                                            num2 = sc.nextInt();

                                            for (int j = 0; j < 1; ) {

                                                if (num2 == 1) {
                                                    j++;
                                                    i3++;
                                                } else if (num2 == 2) {
                                                    j++;
                                                    i3++;
                                                    i2++;
                                                } else {
                                                    System.out.println("输入错误，是否继续修改(是请按1，否请按2):");
                                                    num2 = sc.nextInt();
                                                }


                                            }


                                        } else if (num1 == 2) {//修改名字

                                            System.out.println("请输入学生新名字:" + "(原名字:" + list.get(indexes(id, list)).getName() + ")");
                                            String newName = sc.next();
                                            list.get(indexes(id, list)).setName(newName);
                                            System.out.println("是否继续修改(是请按1，否请按2):");
                                            num2 = sc.nextInt();

                                            for (int j = 0; j < 1; ) {

                                                if (num2 == 1) {
                                                    j++;
                                                    i3++;
                                                } else if (num2 == 2) {
                                                    j++;
                                                    i3++;
                                                    i2++;
                                                } else {
                                                    System.out.println("输入错误，是否继续修改(是请按1，否请按2):");
                                                    num2 = sc.nextInt();
                                                }

                                            }

                                        } else if (num1 == MODIFYING_STUDENT) {//修改年龄

                                            System.out.println("请输入学生新年龄:" + "(原年龄:" + list.get(indexes(id, list)).getAge() + ")");
                                            String newAge = sc.next();
                                            list.get(indexes(id, list)).setAge(newAge);
                                            System.out.println("是否继续修改(是请按1，否请按2):");
                                            num2 = sc.nextInt();

                                            for (int j = 0; j < 1; ) {

                                                if (num2 == 1) {
                                                    j++;
                                                    i3++;
                                                } else if (num2 == 2) {
                                                    j++;
                                                    i3++;
                                                    i2++;
                                                } else {
                                                    System.out.println("输入错误，是否继续修改(是请按1，否请按2):");
                                                    num2 = sc.nextInt();
                                                }

                                            }

                                        } else if (num1 == 4) {//修改家庭住址

                                            System.out.println("请输入学生新家庭住址:" + "(原家庭住址:" + list.get(indexes(id, list)).getHome() + ")");
                                            String newHome = sc.next();
                                            list.get(indexes(id, list)).setHome(newHome);
                                            System.out.println("是否继续修改(是请按1，否请按2):");
                                            num2 = sc.nextInt();

                                            for (int j = 0; j < 1; ) {

                                                if (num2 == 1) {
                                                    j++;
                                                    i3++;
                                                } else if (num2 == 2) {
                                                    j++;
                                                    i3++;
                                                    i2++;
                                                } else {
                                                    System.out.println("输入错误，是否继续修改(是请按1，否请按2):");
                                                    num2 = sc.nextInt();
                                                }

                                            }

                                        } else {
                                            System.out.println("输入错误，请重新输入对应数字:");
                                        }

                                    }

                                } else {
                                    System.out.println("该学生不存在");//此处返回到菜单
                                }


                            } else {//这里不需要再写else if（num2 == 2）了，因为已经添加到每个修改模块中去了
                                System.out.println("输入错误，继续修改(是请按1，否请按2):");
                                num2 = sc.nextInt();
                            }
                        }

                    }
                    //4.查询信息模块
                } else if (num == INQUIRE_ABOUT_STUDENTS) {

                    int num3 = 1;

                    for (int i5 = 0; i5 < 1; ) {//这个循环为了让用户自行选择是否继续查询，
                        // 如果“是”就可以一直查询信息，如果“不是”就停止查询信息

                        if (list.size() == 0) {
                            System.out.println("系统中还未添加学生信息，请先添加:");
                            i5++;
                        } else {

                            if (num3 == 1) {

                                System.out.println("请输入要查询的学生ID:");
                                String id = sc.next();

                                if (indexes(id, list) >= 0) {
                                    System.out.println("学生信息如下:");
                                    System.out.println("名字:" + list.get(indexes(id, list)).getName());
                                    System.out.println("年龄:" + list.get(indexes(id, list)).getAge());
                                    System.out.println("家庭住址:" + list.get(indexes(id, list)).getHome());
                                    System.out.println("继续查询请按“1”，不查询请按“2”");
                                    num3 = sc.nextInt();
                                } else {
                                    System.out.println("该学生不存在");//此处返回到菜单
                                }


                            } else if (num3 == 2) {
                                i5++;
                            } else {
                                System.out.println("输入错误，是否继续查询(是请按1，否请按2):");
                                num3 = sc.nextInt();
                            }


                        }


                    }
                } else {
                    System.out.println("没有该功能，请重新进入系统");
                }

            } else if (number == 22) {//退出系统
                System.out.println("已退出系统");
                i1++;
            } else {
                System.out.println("输入错误，请重新选择");
            }
        }
    }

    public static int indexes(String id, ArrayList<Student> list) {
        //根据ID查询该学生是否存在,若存在就返回其索引。不存在就返回-1
        int indexes = -1;
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())) {
                indexes = i;
            }
        }
        return indexes;
    }

    public static boolean soleId(String id, ArrayList<Student> list) {
        //判断ID是否重复
        boolean b = false;
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())) {
                b = true;
                break;
            }
        }
        return b;
    }

    public static boolean soleNewId(String newId, ArrayList<Student> list) {
        //判断newID是否重复
        boolean b = false;
        for (int i = 0; i < list.size(); i++) {
            if (newId.equals(list.get(i).getId())) {
                b = true;
                break;
            }
        }
        return b;
    }

    public static boolean user(String trueUserName, String truePassword, String userName, String password) {
        //判断输入的用户名和密码是否都正确
        return userName.equals(trueUserName) && password.equals(truePassword);
    }
}
