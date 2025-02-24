#include <iostream>
using namespace std;

class A {
    int x, y;
public:
    A(int a) {  // First constructor
        x = a;
        y = 0;
        cout << "A(int) called\n";
    }
    A(int a, int b) {  // Second constructor
        x = a;
        y = b;
        cout << "A(int, int) called\n";
    }
    void show() {
        cout << "x: " << x << ", y: " << y << endl;
    }
};

class B : public A {
    int age;
public:
    // Choosing to call A(int, int)
    B(int a, int age) : A(a) {  
        this->age = age;
        cout << "B(int, int, int) called\n";
    }
    void show() {
        cout << "age: " << age << endl;
    }
};

int main() {
    B obj(20, 30);

    obj.A::show(); // Calling A's show()
    obj.show();    // Calling B's show()
}
