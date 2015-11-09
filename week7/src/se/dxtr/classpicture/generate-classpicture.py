def main():
    for i in range(20):
        print(30)
        names = [('a' * i).capitalize() for i in range(1, 31)]
        for name in names:
            print(name)
        print(15)
        for j in range(15):
            print('{} {}'.format(names[j], names[j+15]))


if __name__ == '__main__':
    main()
