import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double piEstimate = 0.0;
        for (int i = 1; i <= n; i++)
        {
            if (i % 2 == 0)
            {
                piEstimate -= 4.0/(2*i-1);
            }
            else
            {
                piEstimate += 4.0/(2*i-1);
            }
        }
        System.out.printf("%6f",piEstimate);
    }
}