package pl.jaczewski.interviews;

class SingletonExample {
     public static void main(String[] args) {
          // .main() is an example of a different class - made for testing purposes
          SingletonExample.getInstance().print();
     }

     private static SingletonExample instance = null;

     private SingletonExample() {
     }

     public static synchronized SingletonExample getInstance() {
          if (instance == null) {
               instance = new SingletonExample();
          }
          return instance;
     }

     // .print() - testing purpose
     public void print() {
          System.out.println("Printing...");
     }
}
