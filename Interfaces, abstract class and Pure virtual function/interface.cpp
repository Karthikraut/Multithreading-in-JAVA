#include<iostream>
using namespace std;
class Animal{
public:
    virtual string name()=0;
};

class Dog: Animal{
    string name() override{
        return "Dog";
    }
};

class Cats: Animal{
    string name() override{
        return "Cat";
    }
};

class Lion: Animal{
   public:
    string name() override{
        return "Lion";
    }
};

int main(){
    Lion a;
    cout<<a.name();
}