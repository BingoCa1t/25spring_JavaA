import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        Three();
    }

    public static void One()
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cat = new int[n];
        int[] dog = new int[n];
        for (int i = 0; i < n; i++)
        {
            cat[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++)
        {
            dog[i] = sc.nextInt();
        }
        int checkNumber = sc.nextInt() - 1;
        System.out.println((cat[checkNumber] == -1 ? 1 : 0) + (dog[checkNumber] == -1 ? 1 : 0));
    }

    public static void Two()
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cat = new int[n];
        int[] dog = new int[n];
        for (int i = 0; i < n; i++)
        {
            cat[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++)
        {
            dog[i] = sc.nextInt();
        }
        RepairData(cat);
        RepairData(dog);
        int checkNumber = sc.nextInt() - 1;
        if (cat[checkNumber] == -1 && dog[checkNumber] == -1)
        {
            System.out.println("ALL DATA LOST");
        }
        else if (cat[checkNumber] == -1)
        {
            System.out.println("CAT DATA LOST");
        }
        else if (dog[checkNumber] == -1)
        {
            System.out.println("DOG DATA LOST");
        }
        else
        {
            System.out.println(cat[checkNumber] + dog[checkNumber]);
        }
    }

    public static void RepairData(int[] data)
    {
        // 修复数据
        int n = data.length;
        for (int i = 0; i < n; i++)
        {
            if (data[i] == -1) {
                //几种情况
                //1、i=0或n-1，取0.8*1或0.8*n-1
                //2、i两边存在-1：不修改
                //3、i两边不为-1，0.4*n-1+0.6*n+1
                if (i == n - 1)
                {
                    if (data[n - 2] != -1)
                    {
                        data[n - 1] = (int) (0.8 * data[n - 2]);
                    }
                }
                else if (i == 0)
                {
                    if (data[1] != -1)
                    {
                        data[0] = (int) (0.8 * data[1]);
                    }
                }
                else
                {
                    if (data[i - 1] != -1 && data[i + 1] != -1)
                    {
                        data[i] = (int) (0.4 * data[i - 1] + 0.6 * data[i + 1]);
                    }
                }
            }
        }
    }

    public static void Three()
    {
        /*输入数据：
        3
        7
        12 45 -1 37 95 30 -1
        -1 -1 62 -1 36 0  -1
        24 61 79 49 77 2  -1
        2 4
         */
        Scanner sc = new Scanner(System.in);
        int month = sc.nextInt();
        int n=sc.nextInt();
        int[][] data = new int[month][n];
        for (int i = 0; i < month; i++)
        {
            for (int j = 0; j < n; j++)
            {
                data[i][j]=sc.nextInt();
            }
        }
        int result=RepairDataThree(data,sc.nextInt()-1,sc.nextInt()-1);
        System.out.println(result==-1?"DATA CANNOT BE REPAIRED":result);
    }

    public static int RepairDataThree(int[][] data,int month,int n)
    {
        //二维表，上下是月份，左右是小区编号
        //因除了最后一种情况外，上下或左右系数均相同，故简化写法
        double cLeftOrRight=0, cUpOrDown=0;
        if(data[month][n]!=-1)//数据存在
        {
            return data[month][n];
        }
        boolean isLeftNullOrEmpty = n==0||data[month][n-1]==-1;
        boolean isRightNullOrEmpty = n+1==data[0].length||data[month][n+1]==-1;
        boolean isUpNullOrEmpty = month==0||data[month-1][n]==-1;
        boolean isDownNullOrEmpty = month+1== data.length||data[month+1][n]==-1;
        //缺三个以上，使用计数器判断是否有三个以上为true
        int count=0;
        if(isLeftNullOrEmpty)count++;
        if(isRightNullOrEmpty)count++;
        if(isUpNullOrEmpty)count++;
        if(isDownNullOrEmpty)count++;
        if(count>=3)return -1;//7
        //都不缺1
        if(!isLeftNullOrEmpty&&!isRightNullOrEmpty&&!isUpNullOrEmpty&&!isDownNullOrEmpty)
        {
            cLeftOrRight=0.3;
            cUpOrDown =0.2;
        }
        //缺左且右，但不缺上下5
        else if(isLeftNullOrEmpty&&isRightNullOrEmpty)
        {
            cUpOrDown =0.5;
        }
        //缺左或右,但不缺上下2
        else if((isLeftNullOrEmpty||isRightNullOrEmpty)&&!isUpNullOrEmpty&&!isDownNullOrEmpty)
        {
            cLeftOrRight=0.3;
            cUpOrDown =0.35;
        }
        //缺上且下，但不缺左右。此为特殊情况6
        else if(isUpNullOrEmpty&&isRightNullOrEmpty)
        {
            return (int)(data[month][n-1]*0.4+data[month][n+1]*0.6);
        }
        //缺上或下，但不缺左右3
        else if(!isLeftNullOrEmpty&&!isRightNullOrEmpty&&(!isUpNullOrEmpty||!isDownNullOrEmpty))
        {
            cLeftOrRight = 0.4;
            cUpOrDown = 0.2;
        }
        //缺上或下且缺左或右4
        else if((!isLeftNullOrEmpty||!isRightNullOrEmpty)&&(!isUpNullOrEmpty||!isDownNullOrEmpty))
        {
            cLeftOrRight = 0.6;
            cUpOrDown = 0.4;
        }
        double result=0;
        if(!isLeftNullOrEmpty)result+=cLeftOrRight*data[month][n-1];
        if(!isRightNullOrEmpty)result+=cLeftOrRight*data[month][n+1];
        if(!isUpNullOrEmpty)result+=cUpOrDown*data[month-1][n];
        if(!isDownNullOrEmpty)result+=cUpOrDown*data[month+1][n];
        return (int)result;


        /*
        else
        {
            if(month+1== data.length)//month在下边界
            {
                if(n+1==data[0].length)//month在下且n在右
                {
                    cLeft=0;
                    cRight=0.3;
                    cUpOrDown =0.35;
                    cDown=0.35;
                }
                else if(n==0)//month在下且n在左
                {

                }
                else//month在下且n不在边界
                {
                    cLeft=0.4;
                    cRight=0.4;
                    cUpOrDown =0.2;
                    cDown=0;
                }
            }
            else if(month==0)//month在上边界
            {
                if(n==0)//month在上且n在左
                {

                }
                else if(n==data[0].length)//month在上且n在右
                {

                }
                else//month在上且n不在边界
                {
                    cLeft=0.4;
                    cRight=0.4;
                    cUpOrDown =0;
                    cDown=0.2;
                }
            }
            else if(n==0)//month不在边界且n在左
            {
                cLeft=0;
                cRight=0.3;
                cUpOrDown =0.35;
                cDown=0.35;
            }
            else if (n==data[0].length)//month不在边界且n在右
            {
                cLeft=0.3;
                cRight=0;
                cUpOrDown =0.35;
                cDown=0.35;
            }
            else//month不在边界且n不在边界
            {
                cLeft=0.3;
                cRight=0.3;
                cUpOrDown =0.2;
                cDown=0.2;
            }
        }
        return 0;*/
    }
}