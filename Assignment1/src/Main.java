import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Four();
    }
    public static void One()
    {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int[] a=new int[n];
        for (int i=0;i<n;i++)
        {
            a[i]=input.nextInt();
        }
        for (int i=0;i<n;i++)
        {
            System.out.println(a[i]%10);
        }
    }
    public static void Two()
    {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        int[] a=new int[3];
        a[0]=n%10;
        a[1]=((n-a[0])/10)%10;
        a[2]=(n-a[0]-a[1]*10)/100;
        for (int i=0;i<3;i++)
        {
            int temp;
            for (int j=0;j<2-i;j++)
            {
                if(a[j]>a[j+1])
                {
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        if (m==1)
        {
            for (int i=0;i<3;i++)
            {
                System.out.print(a[i]);
            }
        }
        else
        {
            for (int i=0;i<3;i++)
            {
                System.out.print(a[2-i]);
            }
        }
    }
    public static void Three()
    {
        Scanner input=new Scanner(System.in);
        int min=input.nextInt();
        int max=input.nextInt();
        for(int i=min;i<=max;i++)
        {
            boolean b=IsPerfectNumber(i);
            if(b)
            {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
    public static boolean IsPerfectNumber(int a)
    {
        int sum=0;
        for(int i=1;i<a;i++)
        {
            if(a%2==0)
            {
                if ((double) a / i < 2.0)
                {
                    break;
                }
            }
            else
            {
                if ((double) a / i < 3.0)
                {
                    break;
                }
            }
            if(a%i==0)
            {
                sum+=i;
            }

        }
        return a == sum;
    }
    public static void Four()
    {
        //C 30
        //3
        //K 100
        //C 100
        //F 100
        //All use Celsius Unit
        Scanner input=new Scanner(System.in);
        String targetTemperatureUnit=input.next();// C
        //nextInt() next() String
        //int n=Integer.parseInt(s);"36"  36 "36"
        double targetTemperature=ToCelsiusTemperature(Double.parseDouble(input.next()),targetTemperatureUnit);//"30"->30
        int n= Integer.parseInt(input.next());
        double[] temperature=new double[n];
        int exitIndex=0;
        double minT=Double.MAX_VALUE;
        for (int i=0;i<n;i++)
        {
            String temperatureUnit=input.next();
            double temp= Double.parseDouble(input.next());
            temperature[i]=ToCelsiusTemperature(temp,temperatureUnit);
            if(Math.abs(temperature[i]-targetTemperature)<minT)
            {
                exitIndex=i+1;
                minT=Math.abs(temperature[i]-targetTemperature);
            }
        }
        input.close();
        System.out.println(exitIndex);
    }
    public static double ToCelsiusTemperature(double temperature,String temperatureUnit)
    {
        double result=0;
        switch(temperatureUnit)
        {
            case "K":
                result = temperature-273.15;
                break;
            case "F":
                result = (temperature-32.0)*5.0/9.0;
                break;
            case "C":
                result= temperature;
                break;
        }
        return result;
    }
}