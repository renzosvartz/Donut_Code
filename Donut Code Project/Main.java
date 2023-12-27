import java.io.IOException;
import java.util.*;

public class Main 
{
    public static Integer[][][] torus_xyz;
    public static Integer[][][] newtorus_xyz;
    public static double A = Math.PI/13;
    public static double B = Math.PI/13;

    public static void main(String args[]) throws IOException
    {
        //x: 0 to 150
        //y: 0 to 50

        /* Grid */
        /*
        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                if ((x == 0 && y == 0) || (x == 49 && y == 49))
                {
                    System.out.print("@");
                }
                else
                {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        */
        
        /* Circle */
        /*
        clearScreen(); 
        Character[][] circle = initiateCircle();
        printCircle(circle);
        */
        /*
        for (int i = 0; i < 100; i++)
        {
         
            printDonut(circle);
            
            
            try 
            {
              Thread.sleep(1);
            } 
            catch (InterruptedException e) 
            {
              Thread.currentThread().interrupt();
            }
            
            clearScreen();

            //circle = rotateDonut(circle);
        }
        */

        /* Donut */
        clearScreen(); 
        Character[][] torus = initiateTorus(0);
        //printTorus(torus);
        //printTorusRT();
          
        for (int i = 0; i <= 200; i++)
        {
            


            clearScreen();
            print(initiateTorus(i));
            //torus = rotateTorus(i);
            //if (i % 2 == 0)
                //printTorus(torus);
            //else
                //printTorusD();
                //printTorusRT(i);
            
        }
        
        
        
        /*
        Character[][][] points = new Character[101][101][101];
        */
        
    }   

    public static void print(Character[][] torus) 
    {
        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                System.out.print(torus[x][y]);
            }
            System.out.println();
        }
    }

    public static void printTorusRT(int count) 
    {
        int[] l2 = new int[]{50, 50, 150};

        Integer[][] tdtorus = new Integer[50][50];

        for(int x = 0; x <= 49; x++)
        {
            for(int y = 0; y <= 49; y++)
            {
                tdtorus[x][y] = Integer.MAX_VALUE;
            }
        }

        for (int y = 49; y >= 0; y--)
        {
            for (int x = 49; x >= 0; x--)
            {
                for (int z = 49; z >= 0; z--)
                {

                    if (newtorus_xyz[x][y][z] != 0)
                    {
                        
                        /*
                        double nx = (-646*x + 16150);
                        double ny = (-646*y + 16150);
                        double nz = (2*z - 50);
                        
                        int[] res = matrix_vector_multiplication(new int[]{(int) nx, (int) ny, (int) nz, 1}, count);
                        nx = res[0];
                        ny = res[1];
                        nz = res[2];
                        */
                        
                        /*
                        int[] res = matrix_vector_multiplication(new int[]{(int) nx, (int) ny, (int) nz, 1}, count);
                        nx = res[0];
                        ny = res[1];
                        nz = res[2];
                        */                        
                        /*
                        double nx = (-646*x + 16150) + 25;
                        double ny = (-646*y + 16150) + 25;
                        double nz = (2*z - 50);
                        */

                        double nx = (-646*x + 16150);
                        double ny = (-646*y + 16150);
                        double nz = (2*z - 50);

                        double Nx = nx / Math.sqrt(nx*nx + ny*ny + nz*nz);
                        double Ny = ny / Math.sqrt(nx*nx + ny*ny + nz*nz);
                        double Nz = nz / Math.sqrt(nx*nx + ny*ny + nz*nz);

                        int brightness = (int) (((Ny - Nz) * 8) + ((Nx - Nz) * 8));
                    
                        int xp = (int) (x / (1 - (z / 150.0)));// <= 49 ? (int) (x / (1 - (z / 150.0))) : 49;
                        int yp = (int) (y / (1 - (z / 150.0)));// <= 49 ? (int) (y / (1 - (z / 150.0))) : 49;
                        tdtorus[xp][yp] = brightness;

                    }
                }

                //System.out.println(max + " " + min);

            }
        }

        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                if (tdtorus[x][y] == Integer.MAX_VALUE)
                    System.out.print(' ');
                
                else if (tdtorus[x][y] >= 12)
                {
                    System.out.print("@");
                }
                else if (tdtorus[x][y] == 11)
                {
                    System.out.print("$");
                }
                else if (tdtorus[x][y] == 10)
                {
                    System.out.print("#");
                }
                else if (tdtorus[x][y] == 9)
                {
                    System.out.print("*");
                }
                else if (tdtorus[x][y] == 8)
                {
                    System.out.print("!");
                }
                else if (tdtorus[x][y] == 7)
                {
                    System.out.print("=");
                }
                else if (tdtorus[x][y] == 6)
                {
                    System.out.print(";");
                }
                else if (tdtorus[x][y] == 5)
                {
                    System.out.print(":");
                }
                else if (tdtorus[x][y] == 4)
                {
                    System.out.print("~");
                }
                else if (tdtorus[x][y] == 3)
                {
                    System.out.print("-");
                }
                else if (tdtorus[x][y] == 2)
                {
                    System.out.print(",");
                }
                else if (tdtorus[x][y] <= 1)
                {
                    System.out.print(".");
                }
                
                
            }
            System.out.println();
        }

    }

    public static void printTorusD() 
    {

        Integer[][] tdtorus = new Integer[50][50];

        for(int x = 0; x <= 49; x++)
        {
            for(int y = 0; y <= 49; y++)
            {
                tdtorus[x][y] = Integer.MAX_VALUE;
            }
        }

        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                for (int z = 0; z <= 49; z++)
                {
                    if (newtorus_xyz[x][y][z] != 0)
                    {
                        double dist = Math.sqrt((x-25)*(x-25) + (y-25)*(y-25) + (z-150)*(z-150)); 

                        int xp = (int) (x / (1 - (z / 150.0)));// <= 49 ? (int) (x / (1 - (z / 150.0))) : 49;
                        int yp = (int) (y / (1 - (z / 150.0)));// <= 49 ? (int) (y / (1 - (z / 150.0))) : 49;

                        tdtorus[xp][yp] = (int) Math.min(tdtorus[xp][yp], dist);
                        //tdtorus[xp][yp] = (int) dist;
                        //tdtorus[(int) (25*x/(z+125))][(int) (25*y/(z+125))] = (int) dist;
                    }
                }
            }
        }

        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                if (tdtorus[x][y] == Integer.MAX_VALUE)
                    System.out.print(' ');
                
                else if (tdtorus[x][y] >= 0 && tdtorus[x][y] <= 112)
                {
                    System.out.print("@");
                }
                else if (tdtorus[x][y] >= 113 && tdtorus[x][y] <= 114)
                {
                    System.out.print("$");
                }
                else if (tdtorus[x][y] >= 115 && tdtorus[x][y] <= 116)
                {
                    System.out.print("#");
                }
                else if (tdtorus[x][y] >= 117 && tdtorus[x][y] <= 118)
                {
                    System.out.print("*");
                }
                else if (tdtorus[x][y] >= 119 && tdtorus[x][y] <= 120)
                {
                    System.out.print("!");
                }
                else if (tdtorus[x][y] >= 121 && tdtorus[x][y] <= 122)
                {
                    System.out.print("=");
                }
                else if (tdtorus[x][y] >= 123 && tdtorus[x][y] <= 124)
                {
                    System.out.print(":");
                }
                else if (tdtorus[x][y] >= 125 && tdtorus[x][y] <= 126)
                {
                    System.out.print(":");
                }
                else if (tdtorus[x][y] >= 127 && tdtorus[x][y] <= 128)
                {
                    System.out.print("~");
                }
                else if (tdtorus[x][y] >= 129 && tdtorus[x][y] <= 130)
                {
                    System.out.print("-");
                }
                else if (tdtorus[x][y] >= 131 && tdtorus[x][y] <= 132)
                {
                    System.out.print(",");
                }
                else
                {
                    System.out.print(".");
                }
                
                
            }
            System.out.println();
        }
    }

    public static Character[][] initiateTorus(int c) 
    {
        torus_xyz = new Integer[50][50][50];
        Character[][] tdtorus = new Character[50][50];
        Double[][] zbuffer = new Double[50][50];

        for(int x = 0; x <= 49; x++)
        {
            for(int y = 0; y <= 49; y++)
            {
                for(int z = 0; z <= 49; z++)
                {
                    torus_xyz[x][y][z] = 0;
                }
                zbuffer[x][y] = 0.0;
                tdtorus[x][y] = ' ';
            }
        }

        /* With Projection */
        for (double theta = 0; theta < 2*Math.PI; theta += .07)
        {
            for (double phi = 0; phi < 2*Math.PI; phi += .02)
            {
                double x = (25 + (9 + (5*Math.cos(theta)))*Math.cos(phi)); 
                double y = (25 + (9 + (5*Math.cos(theta)))*Math.sin(phi));
                double z = (25 + 5*Math.sin(theta));

                double[] res = matrix_vector_multiplication(new double[]{x, y, z, 1}, c);
                double nx = res[0];
                double ny = res[1];
                double nz = res[2];
                double ooz = 1/nz;

                int xp = (int) (nx / (1 - (nz / 150.0)));
                int yp = (int) (ny / (1 - (nz / 150.0)));

                double L = Math.abs(Math.cos(phi)*Math.cos(theta)*Math.sin(c*B) - Math.cos(c*A)*Math.cos(theta)*Math.sin(phi) - Math.sin(c*A)*Math.sin(theta) + Math.cos(c*B)*((Math.cos(c*A)*Math.sin(theta) - Math.cos(theta)*Math.sin(c*A)*Math.sin(phi))));

                if (L > 0)
                {
                    if (ooz > zbuffer[xp][yp])
                    {
                        zbuffer[xp][yp] = ooz;
                        String str = ".,-~:;=!*#$@";
                        int lum = (int) (L * 8);
                        tdtorus[xp][yp] = str.charAt(lum);
                    }
                }

                /* Projected Frequencies */
                //tdtorus[xp][yp]++;
                //tdtorus[(int) (5*x/(z+125))][(int) (5*y/(z+125))]++;
            }
        }

        return tdtorus;
    }
    
    public static void printTorus(Integer[][] torus) 
    {
        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                if (torus[x][y] == 0)
                    System.out.print(' ');
                
                else if (torus[x][y] >= 1 && torus[x][y] <= 26)
                {
                    System.out.print(".");
                }
                else if (torus[x][y] >= 27 && torus[x][y] <= 28)
                {
                    System.out.print(",");
                }
                else if (torus[x][y] >= 29 && torus[x][y] <= 32)
                {
                    System.out.print("-");
                }
                else if (torus[x][y] >= 33 && torus[x][y] <= 38)
                {
                    System.out.print("~");
                }
                else if (torus[x][y] >= 39 && torus[x][y] <= 41)
                {
                    System.out.print(":");
                }
                else if (torus[x][y] >= 42 && torus[x][y] <= 52)
                {
                    System.out.print(";");
                }
                else if (torus[x][y] >= 52 && torus[x][y] <= 63)
                {
                    System.out.print("*");
                }
                else if (torus[x][y] >= 63 && torus[x][y] <= 74)
                {
                    System.out.print("!");
                }
                else if (torus[x][y] >= 75 && torus[x][y] <= 118)
                {
                    System.out.print("#");
                }
                else if (torus[x][y] >= 119 && torus[x][y] <= 200)
                {
                    System.out.print("$");
                }
                else
                {
                    System.out.print("@");
                }
                
            }
            System.out.println();
        }
    }
    
    /*
    public static Integer[][] rotateTorus(int count) 
    {
        newtorus_xyz = new Integer[50][50][50];
        Integer[][] newTorus = new Integer[50][50];

        for(int x = 0; x <= 49; x++)
        {
            for(int y = 0; y <= 49; y++)
            {
                for(int z = 0; z <= 49; z++)
                {
                    newtorus_xyz[x][y][z] = 0;
                }
                newTorus[x][y] = 0;
            }
        }

        //apply transformation to [x][y][z], save into [x][y]++
        for(int x = 0; x <= 49; x++)
        {
            for(int y = 0; y <= 49; y++)
            {
                for(int z = 0; z <= 49; z++)
                {
                    if (torus_xyz[x][y][z] != 0)
                    {
                        //the point at x0, y0, z0, ends up at xt, yt, zt after count rotations
                        int[] res = new int[]{x, y, z, 1};
                        
                        res = matrix_vector_multiplication(new int[]{res[0], res[1], res[2], 1}, count);
                        
                        /*
                        System.out.println(x + " " + y + " " + z + "\n to ");
                        System.out.println(res[0] + " " + res[1] + " " + res[2]);
                        

                        newtorus_xyz[res[0]][res[1]][res[2]]++;

                        /*Project point
                        int xp = (int) (res[0] / (1 - (res[2] / 150.0))) <= 49 ? (int) (res[0] / (1 - (res[2] / 150.0))) : 49;
                        int yp = (int) (res[1] / (1 - (res[2] / 150.0))) <= 49 ? (int) (res[1] / (1 - (res[2] / 150.0))) : 49;

                        newTorus[xp][yp] += torus_xyz[x][y][z];
                    }
                }

            }
        }

        return newTorus;
    }
    */

    public static double[] matrix_vector_multiplication(double[] x, int count)
    {
        double[] b = new double[4];
        double[] b2 = new double[4];
        double[] b4 = new double[4];
        double[] b3 = new double[4];

        double[][] A = {{1, 0, 0, -25}, {0, 1, 0, -25}, {0, 0, 1, -25}, {0, 0, 0, 1}}; 

        
        b[0] = (double) ((A[0][0] * x[0]) + (A[0][1] * x[1]) + (A[0][2] * x[2]) + (A[0][3] * x[3]));
        b[1] = (double) ((A[1][0] * x[0]) + (A[1][1] * x[1]) + (A[1][2] * x[2]) + (A[1][3] * x[3]));
        b[2] = (double) ((A[2][0] * x[0]) + (A[2][1] * x[1]) + (A[2][2] * x[2]) + (A[2][3] * x[3]));
        b[3] = (double) ((A[3][0] * x[0]) + (A[3][1] * x[1]) + (A[3][2] * x[2]) + (A[3][3] * x[3]));


        double[][] A2 = {{1, 0, 0, 0}, {0, Math.cos((count * Math.PI/13)), 1*Math.sin(count * Math.PI/13), 0}, {0, -1*Math.sin(count * Math.PI/13), Math.cos((count * Math.PI/13)), 0}, {0, 0, 0, 1}}; 
                                                        
                                                        /* 
                                                            
                                                            c   -s    0  0
                                                            s    c    0  0
                                                            0    0    1  0
                                                            0    0    0  1
                                                            
                                                            X = x y z 1
                                                        */


        b2[0] = (double) ((A2[0][0] * b[0]) + (A2[0][1] * b[1]) + (A2[0][2] * b[2]) + (A2[0][3] * b[3]));
        b2[1] = (double) ((A2[1][0] * b[0]) + (A2[1][1] * b[1]) + (A2[1][2] * b[2]) + (A2[1][3] * b[3]));
        b2[2] = (double) ((A2[2][0] * b[0]) + (A2[2][1] * b[1]) + (A2[2][2] * b[2]) + (A2[2][3] * b[3]));
        b2[3] = (double) ((A2[3][0] * b[0]) + (A2[3][1] * b[1]) + (A2[3][2] * b[2]) + (A2[3][3] * b[3]));

        //

        double[][] A4 = {{Math.cos((count * Math.PI/13)), 1*Math.sin(count * Math.PI/13), 0, 0}, {-1*Math.sin(count * Math.PI/13), Math.cos((count * Math.PI/13)), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}; 
        //double[][] A4 = {{1, 0, 0, 0}, {0, Math.cos((count * Math.PI/13)), 1*Math.sin(count * Math.PI/13), 0}, {0, -1*Math.sin(count * Math.PI/13), Math.cos((count * Math.PI/13)), 0}, {0, 0, 0, 1}};                                           
                                                        /* 
                                                            
                                                            c   -s    0  0
                                                            s    c    0  0
                                                            0    0    1  0
                                                            0    0    0  1
                                                            
                                                            X = x y z 1
                                                        */


        b4[0] = (double) ((A4[0][0] * b2[0]) + (A4[0][1] * b2[1]) + (A4[0][2] * b2[2]) + (A4[0][3] * b2[3]));
        b4[1] = (double) ((A4[1][0] * b2[0]) + (A4[1][1] * b2[1]) + (A4[1][2] * b2[2]) + (A4[1][3] * b2[3]));
        b4[2] = (double) ((A4[2][0] * b2[0]) + (A4[2][1] * b2[1]) + (A4[2][2] * b2[2]) + (A4[2][3] * b2[3]));
        b4[3] = (double) ((A4[3][0] * b2[0]) + (A4[3][1] * b2[1]) + (A4[3][2] * b2[2]) + (A4[3][3] * b2[3]));

        //

        double[][] A3 = {{1, 0, 0, 25}, {0, 1, 0, 25}, {0, 0, 1, 25}, {0, 0, 0, 1}}; 
                                                        
                                                        /* 
                                                            
                                                            c   -s    0  0
                                                            s    c    0  0
                                                            0    0    1  0
                                                            0    0    0  1
                                                            
                                                            X = x y z 1
                                                        */


        b3[0] = (double) ((A3[0][0] * b4[0]) + (A3[0][1] * b4[1]) + (A3[0][2] * b4[2]) + (A3[0][3] * b4[3]));
        b3[1] = (double) ((A3[1][0] * b4[0]) + (A3[1][1] * b4[1]) + (A3[1][2] * b4[2]) + (A3[1][3] * b4[3]));
        b3[2] = (double) ((A3[2][0] * b4[0]) + (A3[2][1] * b4[1]) + (A3[2][2] * b4[2]) + (A3[2][3] * b4[3]));
        b3[3] = (double) ((A3[3][0] * b4[0]) + (A3[3][1] * b4[1]) + (A3[3][2] * b4[2]) + (A3[3][3] * b4[3]));

        /*
        System.out.println(x[0] + " " + x[1] + " " + x[2] + " " + x[3]);
        System.out.println(b[0] + " " + b[1] + " " + b[2] + " " + b[3]);
        System.out.println(b2[0] + " " + b2[1] + " " + b2[2] + " " + b2[3]);
        System.out.println(b3[0] + " " + b3[1] + " " + b3[2] + " " + b3[3]);
        System.out.println();
        */

        return b3;
    }

    /*
    public static int[] matrix_vector_multiplication2(int[] x, int count)
    {
        int[] b = new int[4];

        double[][] A = {{Math.cos((count * Math.PI/13)), 0, -1*Math.sin(count * Math.PI/13), 0}, {0, 1, 0, 0}, {1*Math.sin(count * Math.PI/13), 0, Math.cos((count * Math.PI/13)), 0}, {0, 0, 0, 1}}; 

        
        b[0] = (int) ((A[0][0] * x[0]) + (A[0][1] * x[1]) + (A[0][2] * x[2]) + (A[0][3] * x[3]));
        b[1] = (int) ((A[1][0] * x[0]) + (A[1][1] * x[1]) + (A[1][2] * x[2]) + (A[1][3] * x[3]));
        b[2] = (int) ((A[2][0] * x[0]) + (A[2][1] * x[1]) + (A[2][2] * x[2]) + (A[2][3] * x[3]));
        b[3] = (int) ((A[3][0] * x[0]) + (A[3][1] * x[1]) + (A[3][2] * x[2]) + (A[3][3] * x[3]));

        return b;
    }
    */

    public static Character[][] initiateCircle() 
    {
        Character[][] circle = new Character[50][50];

        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                circle[x][y] = ' ';
            }
        }

        for (int y = 45; y >= 5; y--)
        {
            int x_soln_1 = (int) Math.sqrt((-1*y*y) + (50*y) - 225) + 25;
            int x_soln_2 = (int) (-1*Math.sqrt((-1*y*y) + (50*y) - 225)) + 25;

            circle[x_soln_1][y] = 'X';
            circle[x_soln_2][y] = 'X';
        }

        return circle;
    }

    public static void printCircle(Character[][] circle)
    {
        for (int y = 49; y >= 0; y--)
        {
            for (int x = 0; x <= 49; x++)
            {
                System.out.print(circle[x][y]);
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
