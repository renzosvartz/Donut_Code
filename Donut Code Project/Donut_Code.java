import java.io.IOException;

public class Donut_Code 
{
    public static Integer[][][] points;
    public static Integer[][][] updated_points;
    public static Character[][] torus;
    public static Double[][] zbuffer;
    public static double R1 = 5;
    public static double R2 = 9;
    public static double A = Math.PI/13;
    public static double B = Math.PI/26;
    public static double K2 = 5;
    public static double screenwidth = 50;
    public static double screenheight = 50; 
    public static double K1 = 150;//screenwidth*K2*3/(8*(R1+R2));

    public static void main(String args[]) throws IOException
    {
        /* Donut */
        for (int i = 0; i <= 200; i++)
        {
            clearScreen(); 
            initiateTorus(i);
            printTorus();
        }
    }

    public static void initiateTorus(int c) 
    {
        torus = new Character[51][51];
        zbuffer = new Double[51][51];

        for(int x = 0; x <= 50; x++)
        {
            for(int y = 0; y <= 50; y++)
            {
                zbuffer[x][y] = 0.0;
                torus[x][y] = ' ';
            }
        }

        /* With Projection */
        for (double theta = 0; theta < 2*Math.PI; theta += .07)
        {
            for (double phi = 0; phi < 2*Math.PI; phi += .02)
            {
                double crosssection_x = R2 + R1*Math.cos(theta);
                double crosssection_y = R1*Math.sin(theta);

                /*
                double x = 25 + crosssection_x*(Math.cos(c*B)*Math.cos(phi) + Math.sin(c*A)*Math.sin(c*B)*Math.sin(phi)) - crosssection_y*Math.cos(c*A)*Math.sin(c*B);
                double y = 25 + crosssection_x*(Math.sin(c*B)*Math.cos(phi) - Math.sin(c*A)*Math.cos(c*B)*Math.sin(phi)) + crosssection_y*Math.cos(c*A)*Math.cos(c*B);
                double z = 25 + Math.cos(c*A)*crosssection_x*Math.sin(phi) + crosssection_y*Math.sin(c*A);
                */

                double x = 25 + 14*Math.cos(theta)*Math.sin(phi); 
                double y = 25 + 14*Math.sin(theta)*Math.sin(phi); 
                double z = 25 + 14*Math.cos(phi);
                

                double ooz = 1/z;

                int xp = (int) (x / (1 - (z / K1)));
                int yp = (int) (y / (1 - (z / K1)));

                //double L = Math.cos(phi)*Math.cos(theta)*Math.sin(c*B) - Math.cos(c*A)*Math.cos(theta)*Math.sin(phi) - Math.sin(c*A)*Math.sin(theta) + Math.cos(c*B)*((Math.cos(c*A)*Math.sin(theta) - Math.cos(theta)*Math.sin(c*A)*Math.sin(phi)));
                double L = ((y - 25) - (z - 25))/14;

                if (L >= 0)
                {
                    if (ooz >= zbuffer[xp][yp])
                    {
                        zbuffer[xp][yp] = ooz;
                        String str = ".,-~:;=!*#$@";
                        int lum = (int) (L * 8) > 11? 11 : (int) (L * 8);
                        torus[xp][yp] = str.charAt(lum);
                    }
                }
            }
        }
    }

    public static void printTorus() 
    {
        for (int y = 50; y >= 0; y--)
        {
            for (int x = 0; x <= 50; x++)
            {
                System.out.print(torus[x][y]);
            }
            System.out.println();
        }
    }

    public static void clearScreen() 
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
