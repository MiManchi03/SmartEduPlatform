import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserTest {
    private static final int LOGIN_ID = 1;//登录账号
    private static final int REGISTER_AN_ACCOUNT = 2;//注册账号
    private static final int FORGOT_ACCOUNT_NUMBER = 3;//忘记账号

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int u = 0;//已创建用户的个数
        ArrayList<User> users = new ArrayList<>();//将被注册的所有用户信息储存
        for (int i = 0; i < 1; ) {
            System.out.println("********欢迎使用学生管理系统*********");
            System.out.println("登录账号请按“1”");
            System.out.println("注册账号请按“2”");
            System.out.println("忘记账号请按“3”");
            //System.out.println("查询所有账号请按“4”");//后台管理使用，用户无法使用此功能
            int number = sc.nextInt();
            switch (number) {
                case LOGIN_ID -> i = getI(u, sc, users, i, r);
                case REGISTER_AN_ACCOUNT -> u = getU(sc, users, u, r);
                case FORGOT_ACCOUNT_NUMBER -> {
                    if (u != 0) {
                        extracted(sc, users, r);
                    } else {
                        System.out.println("用户不存在，请先创建用户");
                    }
                }
                case 4 -> {
                    if (u != 0) {
                        extracted(users);
                    } else {
                        System.out.println("用户不存在，请先创建用户");
                    }
                }
                default -> System.out.println("无该选项，请重新输入:");
            }
        }
    }

    private static int getI(int u, Scanner sc, ArrayList<User> users, int i, Random r) {
        //“1”，账号登录模块
        int time = 3;//记录登录密码的次数，超过三次登录失败
        for (int i1 = 0; i1 < 1; ) {
            System.out.println("******请输入正确的用户名和密码******");
            System.out.println("请输入用户名(长度为3~15且必须有数字和字母组成):");
            String userName = sc.next();
            for (int i2 = 0; i2 < 1; ) {
                if (userNameJudge(userName)) {
                    i2++;
                } else {
                    System.out.println("用户名不合法，请重新输入(长度为3~15且必须有数字和字母组成):");
                    userName = sc.next();
                }
            }
            System.out.println("请输入密码:");
            String password = sc.next();
            if (u != 0) {
                for (int k = 0; k < 1; ) {
                    if (user(users, userName, password)) {
                        k++;
                    } else {
                        System.out.println("【还有" + time + "次机会】");
                        System.out.println("用户名或密码错误，请重新输入:");
                        System.out.println("请输入用户名:");
                        userName = sc.next();
                        for (int i2 = 0; i2 < 1; ) {
                            if (userNameJudge(userName)) {
                                i2++;
                            } else {
                                System.out.println("用户名不合法，请重新输入(长度为3~15且必须有数字和字母组成):");
                                userName = sc.next();
                            }
                        }
                        System.out.println("请输入密码:");
                        password = sc.next();
                        time--;
                        if (time == 0) {
                            System.out.println("登录失败,退出系统");
                            System.exit(0);
                        }
                    }
                }
                extracted(sc, r);//验证码校验
                System.out.println("登录成功");
                i1++;
                i++;
            } else {
                System.out.println("用户不存在，请先创建用户");
                i1++;
            }
        }
        return i;
    }

    private static int getU(Scanner sc, ArrayList<User> users, int u, Random r) {
        //“2”，注册账号，并记录账号的个数
        System.out.println("【开始创建账号】");
        User user = new User();
        System.out.println("请输入身份证:");
        String idCard = sc.next();
        for (int i = 0; i < 1; ) {
            if (idCard(idCard) && idCard.length() == 18) {
                if (!idCardExist(idCard, users)) {
                    user.setTrueIdCard(idCard);
                    i++;
                } else {
                    System.out.println("身份证已被注册,请重新输入:");
                    idCard = sc.next();
                }
            } else {
                System.out.println("身份证输入错误请重新输入:");
                idCard = sc.next();
            }
        }
        System.out.println("请输入手机号:");
        String phoneNumber = sc.next();
        for (int i = 0; i < 1; ) {
            if (phoneNumber(phoneNumber) && phoneNumber.length() == 11) {
                if (!phoneNumberExist(phoneNumber, users)) {
                    user.setTruePhoneNumber(phoneNumber);
                    i++;
                } else {
                    System.out.println("手机号已被注册，请重新输入:");
                    phoneNumber = sc.next();
                }
            } else {
                System.out.println("手机号输入错误请重新输入");
                phoneNumber = sc.next();
            }
        }
        System.out.println("请输入新用户名:");
        for (int i2 = 0; i2 < 1; ) {
            String userName = sc.next();
            if (userNameJudge(userName)) {
                if (!userNameExist(userName, users)) {
                    user.setTrueUserName(userName);
                    i2++;
                } else {
                    System.out.println("用户名已存在，请重新输入:");
                }
            } else {
                System.out.println("用户名不合法，请重新输入(长度为3~15且必须有数字和字母组成):");
            }
        }
        System.out.println("请输入新密码:");
        String password = sc.next();
        for (int i = 0; i < 1; ) {
            if (getEng(sc, password) == 0) {
                System.out.println("请重新输入新密码:");
                password = sc.next();
            } else {
                i++;
            }
        }
        user.setTruePassword(password);
        extracted(sc, r);//验证码校验
        System.out.println("注册成功");
        users.add(user);//可存储多个账号
        u++;
        return u;
    }

    private static int getEng(Scanner sc, String password) {
        //在第二次确认密码时，如果确认次数超过两次就要重新输入新密码
        System.out.println("请确认密码:");
        int eng = 2;
        for (int i = 0; i < 1; ) {
            String password2 = sc.next();
            if (passwordJudge(password, password2) || eng == 0) {
                i++;
            } else {
                eng--;
                System.out.println("密码错误，请再次确认(" + "还剩" + (eng + 1) + "次确认机会):");
            }
        }
        return eng;
    }

    private static void extracted(Scanner sc, ArrayList<User> users, Random r) {
        //“3”,忘记账户模块
        System.out.println("********请输入身份信息********");
        System.out.println("请输入注册时使用的身份证号:");
        String idCard = sc.next();
        for (int i = 0; i < 1; ) {
            if (idCard(idCard) && idCard.length() == 18) {
                i++;
            } else {
                System.out.println("身份证输入错误请重新输入:");
                idCard = sc.next();
            }
        }
        System.out.println("请输入注册时使用的手机号:");
        String phoneNumber = sc.next();
        for (int i = 0; i < 1; ) {
            if (phoneNumber(phoneNumber) && phoneNumber.length() == 11) {
                i++;
            } else {
                System.out.println("手机号输入错误请重新输入");
                phoneNumber = sc.next();
            }
        }
        extracted(sc, r);//验证码校验
        if (judge(idCard, phoneNumber, users)) {
            System.out.println("信息正确，账号信息如下");
            System.out.println("***********************");
            System.out.println("用户名:" + users.get(indexes(idCard, phoneNumber, users)).getTrueUserName());
            System.out.println("密码:" + users.get(indexes(idCard, phoneNumber, users)).getTruePassword());
        } else {
            System.out.println("身份信息输入错误或该身份下无账号");
        }
    }


    private static void extracted(ArrayList<User> users) {
        //“4”，遍历所以账号信息(后台管理使用)
        System.out.println("所以账号信息如下:");
        for (int j = 0; j < users.size(); j++) {
            System.out.println("***********************");
            System.out.println("用户名:" + users.get(j).getTrueUserName());
            System.out.println("密码:" + users.get(j).getTruePassword());
        }
    }

    public static StringBuilder verificationCode(Random r) {
        //生成验证码
        StringBuilder sb = new StringBuilder();
        //将所有大小写字母存入数组中
        char[] allLetter = new char[52];
        int AZ = 65;
        int az = 97;
        for (int i = 0; i < allLetter.length; i++) {
            if (i < 26) {
                allLetter[i] = (char) az;
                az++;
            } else {
                allLetter[i] = (char) AZ;
                AZ++;
            }
        }
        char[] vc = new char[5];//储存验证码元素的容器
        for (int i = 0; i < 4; i++) {//随机四个字母存入vc中
            int letter = r.nextInt(52);
            vc[i] = allLetter[letter];
        }
        for (int i = 0; i < 1; i++) {//随机一个数字存入vc中
            int num = r.nextInt(10) + 48;
            vc[4] = (char) num;
        }
        //打乱vc中的字符
        for (int i = 0; i < vc.length; i++) {
            int random = r.nextInt(5);
            char temp = vc[i];
            vc[i] = vc[random];
            vc[random] = temp;
        }
        for (int i = 0; i < vc.length; i++) {
            sb.append(vc[i]);
        }
        return sb;
    }

    private static void extracted(Scanner sc, Random r) {
        //校验验证码
        System.out.println("请输入验证码:");
        for (int p = 0; p < 1; ) {
            StringBuilder sb = verificationCode(r);
            String trueVc = sb.toString();
            System.out.println("验证码为:【" + trueVc + "】");
            String vc = sc.next();
            if (vcJudge(vc, trueVc)) {
                p++;
            } else {
                System.out.println("验证码输入错误请重新输入:");
            }
        }
    }

    public static boolean user(ArrayList<User> users, String userName, String password) {
        //判断输入的用户名和密码是否都正确
        boolean user = false;
        for (int i = 0; i < users.size(); i++) {
            if (userName.equals(users.get(i).getTrueUserName()) && password.equals(users.get(i).getTruePassword())) {
                user = true;
                break;
            }
        }
        return user;
    }

    public static boolean judge(String idCard, String phoneNumber, ArrayList<User> users) {
        //判断输入的手机号和身份证号是否和注册时的一样
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (idCard.equals(users.get(i).getTrueIdCard()) && phoneNumber.equals(users.get(i).getTruePhoneNumber())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static int indexes(String idCard, String phoneNumber, ArrayList<User> users) {
        //根据输入的正确信息找到该信息下对应账号在集合中的索引
        int indexes = -1;
        for (int i = 0; i < users.size(); i++) {
            if (idCard.equals(users.get(i).getTrueIdCard()) && phoneNumber.equals(users.get(i).getTruePhoneNumber())) {
                indexes = i;
                break;
            }
        }
        return indexes;
    }

    public static boolean idCard(String idCard) {
        //判断输入的身份证是否符合要求
        String regular = "[1-9][0-9]{16}(\\d|x|X)";
        return idCard.matches(regular);
    }

    public static boolean phoneNumber(String phoneNumber) {
        //判断输入的手机号是否符合要求
        String regular = "1[0-9]{10}";
        return phoneNumber.matches(regular);
    }

    public static boolean vcJudge(String vc, String trueVc) {
        //判断验证码输入的是否正确
        return vc.equals(trueVc);
    }

    public static boolean userNameJudge(String userName) {
        //判断用户名是否合法
        //只能是字母和数字的组合且长度为3~15
        String regular = "[\\w&&[^_]]{3,15}";
        return userName.matches(regular);
    }

    public static boolean passwordJudge(String password, String password2) {
        //注册时，判断两次输入密码是相同
        return password2.equals(password);
    }

    public static boolean idCardExist(String idCard, ArrayList<User> users) {
        //判断注册时输入的身份证是否已存在
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (idCard.equals(users.get(i).getTrueIdCard())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean phoneNumberExist(String phoneNumber, ArrayList<User> users) {
        //判断注册时输入的手机号是否已存在
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (phoneNumber.equals(users.get(i).getTruePhoneNumber())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean userNameExist(String userName, ArrayList<User> users) {
        //判断注册时输入的用户名是否已存在
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (userName.equals(users.get(i).getTrueUserName())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
