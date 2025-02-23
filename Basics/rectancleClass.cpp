#include<iostream>
using namespace std;
class rectangle{
    private:
        int l;
        int b;
    public:
        rectangle(){

        }

        int area(int l, int b){
            this->l =l;
            this->b =b;
            return l*b;
        }
        void print(){
            cout<<"length: "<<this->l<<" breadth: "<<this->b;
        }

};
int main(){
    rectangle r1 = rectangle();
    cout<<r1.area(4,5)<<endl;
    r1.print();
}