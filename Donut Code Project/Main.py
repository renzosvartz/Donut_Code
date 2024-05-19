import numpy as np
import math
import concurrent.futures

torus_xyz = None
new_torus_xyz = None
torus_i = None
proj_torus = None
zbuffer = None
A = math.pi / 13
B = math.pi / 13
xo = 25
yo = 25
zo = 25
s = 7

def clear_screen():
    print("\033[H\033[J", end='')

def initiate_torus3(c):
    global torus_xyz, proj_torus, zbuffer, torus_i

    torus_xyz = np.zeros((50, 50, 50), dtype=int)
    proj_torus = np.full((50, 50), ' ', dtype=str)
    zbuffer = np.zeros((50, 50), dtype=float)
    torus_i = np.zeros((50, 50), dtype=int)

    thetas = np.arange(0, 2 * math.pi, 0.07)
    phis = np.arange(0, 2 * math.pi, 0.02)
    str_lum = ".,-~:;=!*#$@"

    for theta in thetas:
        for phi in phis:
            x = 25 + 14 * math.cos(theta) * math.sin(phi)
            y = 25 + 14 * math.sin(theta) * math.sin(phi)
            z = 25 + 14 * math.cos(phi)

            torus_xyz[int(x)][int(y)][int(z)] += 1

            res = matrix_vector_multiplication([x, y, z, 1], c)
            nx, ny, nz = res[:3]
            ooz = 1 / nz

            xp = int(nx / (1 - (nz / 150.0)))
            yp = int(ny / (1 - (nz / 150.0)))

            xp = min(max(xp, 0), 49)
            yp = min(max(yp, 0), 49)

            L = ((ny - 25) - (nz - 25)) / 14

            if L >= 0:
                if ooz >= zbuffer[xp][yp]:
                    zbuffer[xp][yp] = ooz
                    lum = int(L * 8)
                    proj_torus[xp][yp] = str_lum[lum]

            torus_i[xp][yp] += 1

    return proj_torus

def initiate_torus2(c):
    global torus_xyz, proj_torus, zbuffer, torus_i

    torus_xyz = np.zeros((50, 50, 50), dtype=int)
    proj_torus = np.full((50, 50), ' ', dtype=str)
    zbuffer = np.zeros((50, 50), dtype=float)
    torus_i = np.zeros((50, 50), dtype=int)

    us = np.arange(-1, 1.1, 0.1)
    vs = np.arange(-1, 1.1, 0.1)
    ws = np.arange(-1, 1.3, 0.1)
    str_lum = ".,-~:;=!*#$@"

    for u in us:
        for v in vs:
            for w in ws:
                x = xo + s * u
                y = yo + s * v
                z = zo + s * w

                res = matrix_vector_multiplication([x, y, z, 1], c)
                nx, ny, nz = res[:3]
                ooz = 1 / nz

                xp = int(nx / (1 - (nz / 150.0)))
                yp = int(ny / (1 - (nz / 150.0)))

                xp = min(max(xp, 0), 49)
                yp = min(max(yp, 0), 49)

                N = np.zeros(4)
                if x == xo - s:
                    N = [-1, 0, 0, 0]
                elif x == xo + s:
                    N = [1, 0, 0, 0]
                elif y == yo - s:
                    N = [0, -1, 0, 0]
                elif y == yo + s:
                    N = [0, 1, 0, 0]
                elif z == zo - s:
                    N = [0, 0, -1, 0]
                elif z == zo + s:
                    N = [0, 0, 1, 0]

                res = matrix_vector_multiplication(N, c)
                L = (res[1] - res[2])

                if L >= 0:
                    if ooz > zbuffer[xp][yp]:
                        zbuffer[xp][yp] = ooz
                        lum = int(L * 8)
                        proj_torus[xp][yp] = str_lum[lum]

                torus_i[xp][yp] += 1

    return proj_torus

def initiate_torus(c):
    global torus_xyz, proj_torus, zbuffer

    torus_xyz = np.zeros((50, 50, 50), dtype=int)
    proj_torus = np.full((50, 50), ' ', dtype=str)
    zbuffer = np.zeros((50, 50), dtype=float)

    thetas = np.arange(0, 2 * math.pi, 0.07)
    phis = np.arange(0, 2 * math.pi, 0.02)
    str_lum = ".,-~:;=!*#$@"

    for theta in thetas:
        for phi in phis:
            x = 25 + (9 + (5 * math.cos(theta))) * math.cos(phi)
            y = 25 + (9 + (5 * math.cos(theta))) * math.sin(phi)
            z = 25 + 5 * math.sin(theta)

            torus_xyz[int(x)][int(y)][int(z)] += 1

            res = matrix_vector_multiplication([x, y, z, 1], c)
            nx, ny, nz = res[:3]
            ooz = 1 / nz

            xp = int(nx / (1 - (nz / 150.0)))
            yp = int(ny / (1 - (nz / 150.0)))

            xp = min(max(xp, 0), 49)
            yp = min(max(yp, 0), 49)

            L = abs(math.cos(phi) * math.cos(theta) * math.sin(c*B) -
                    math.cos(c*A) * math.cos(theta) * math.sin(phi) -
                    math.sin(c*A) * math.sin(theta) +
                    math.cos(c*B) * (math.cos(c*A) * math.sin(theta) -
                                   math.cos(theta) * math.sin(c*A) * math.sin(phi)))

            if L >= 0:
                if ooz >= zbuffer[xp][yp]:
                    zbuffer[xp][yp] = ooz
                    lum = int(L * 8)
                    proj_torus[xp][yp] = str_lum[lum]

    return proj_torus

def print_torus(torus):
    for y in range(49, -1, -1):
        for x in range(50):
            print(torus[x][y], end='')
        print()

def rotate_torus(count):
    global new_torus_xyz

    new_torus_xyz = np.zeros((50, 50, 50), dtype=int)
    new_torus_i = np.zeros((50, 50), dtype=int)

    for x in range(50):
        for y in range(50):
            for z in range(50):
                if torus_xyz[x][y][z] != 0:
                    res = matrix_vector_multiplication([x, y, z, 1], count)
                    new_x, new_y, new_z = map(int, res[:3])
                    new_torus_xyz[new_x][new_y][new_z] += torus_xyz[x][y][z]

                    xp = int(new_x / (1 - (new_z / 150.0)))
                    yp = int(new_y / (1 - (new_z / 150.0)))
                    xp = min(max(xp, 0), 49)
                    yp = min(max(yp, 0), 49)

                    new_torus_i[xp][yp] += torus_xyz[x][y][z]

    return new_torus_i

def print_torus_freq(torus):
    for y in range(49, -1, -1):
        for x in range(50):
            val = torus[x][y]
            if val == 0:
                print(' ', end='')
            elif 1 <= val <= 26:
                print('.', end='')
            elif 27 <= val <= 28:
                print(',', end='')
            elif 29 <= val <= 32:
                print('-', end='')
            elif 33 <= val <= 38:
                print('~', end='')
            elif 39 <= val <= 41:
                print(':', end='')
            elif 42 <= val <= 52:
                print(';', end='')
            elif 52 <= val <= 63:
                print('*', end='')
            elif 63 <= val <= 75:
                print('=', end='')
            elif 75 <= val <= 89:
                print('!', end='')
            elif 89 <= val <= 111:
                print('#', end='')
            elif 111 <= val <= 999:
                print('@', end='')
        print()

def matrix_vector_multiplication(x, count):
    A = np.array([[1, 0, 0, -25], [0, 1, 0, -25], [0, 0, 1, -25], [0, 0, 0, 1]])
    A2 = np.array([[1, 0, 0, 0], 
                   [0, np.cos(count * np.pi / 13), np.sin(count * np.pi / 13), 0], 
                   [0, -np.sin(count * np.pi / 13), np.cos(count * np.pi / 13), 0], 
                   [0, 0, 0, 1]])
    A4 = np.array([[np.cos(count * np.pi / 13), np.sin(count * np.pi / 13), 0, 0], 
                   [-np.sin(count * np.pi / 13), np.cos(count * np.pi / 13), 0, 0], 
                   [0, 0, 1, 0], 
                   [0, 0, 0, 1]])
    A3 = np.array([[1, 0, 0, 25], 
                   [0, 1, 0, 25], 
                   [0, 0, 1, 25], 
                   [0, 0, 0, 1]])

    b = np.dot(A, x)
    b2 = np.dot(A2, b)
    b4 = np.dot(A4, b2)
    b3 = np.dot(A3, b4)

    return b3

def main():
    global A, B

    A += math.pi / 13
    B += math.pi / 17

    clear_screen()
    torus = initiate_torus(0)

    for i in range(37):
        clear_screen()
        torus_i = rotate_torus(i)
        print_torus_freq(torus_i)

    for i in range(37):
        clear_screen()
        print_torus(initiate_torus(i))
       
    for i in range(37):
        clear_screen()
        print_torus(initiate_torus2(i))

    for i in range(37):
        clear_screen()
        print_torus(initiate_torus3(i))



if __name__ == "__main__":
    main()