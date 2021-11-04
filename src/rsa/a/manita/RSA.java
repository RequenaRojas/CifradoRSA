/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rsa.a.manita;

/**
 *
 * @author sofo9
 */

import java.math.BigInteger;
import java.util.*;
 

public class RSA {
    
    /*
    Vamos a definir nuestro enteros positivos 
    */
    
    int tamprimo;
    BigInteger p,q,n;
    BigInteger fi;
    BigInteger e,d;
    
    public RSA (){
    }
    
    public void RSA (int tamprimo){
        this.tamprimo = tamprimo;
    }
    
    //metodo para generar los num primos
    public void generarPrimo(){
        
        p = new BigInteger(tamprimo, 10, new Random());
        
        do q = new BigInteger(tamprimo, 10, new Random());
        while(q.compareTo(p) == 0);
                
                
    }
    
    //generar las claves
    public void generarClaves(){
        
        /*
        Recordar que n = p*q
        fi = (p-1)(q-1)
        */
        
        n = p.multiply(q);
        
        fi = p.subtract(BigInteger.valueOf(1));
        
        fi = fi.multiply(q.subtract(BigInteger.valueOf(1)));
        
        /*
        como calculabamos a 'e'
        
        e debe ser un coprimo de n tal que:
        
        1 < e < fi(n)
        */
        
        do e = new BigInteger(2*tamprimo, new Random());
        while(e.compareTo(fi) != -1 || (e.gcd(fi).compareTo(BigInteger.valueOf(1)) != 0));
        
        //calcular a d
        // d = e^1 mod fi, inverso multiplicativo de e
        
        d = e.modInverse(fi);
        
    }
    
    //encriptamos con la clave pública
    // e , n
    public BigInteger[] cifrar (String mensaje,BigInteger e,BigInteger n){
        
        int i;
        byte[] temp = new byte[1];
        byte[] digitos =  mensaje.getBytes();
        
        BigInteger[] bigdigitos = new BigInteger[digitos.length];
        
        for(i = 0; i < bigdigitos.length; i++){
            temp[0] = digitos[i];
            
            bigdigitos[i] = new BigInteger(temp);
        }
        
        BigInteger[] cifrado = new BigInteger[bigdigitos.length];
        
        for(i=0; i< bigdigitos.length;i++){
           //formula
           // Cifr = M^e mod n
           
           cifrado[i] = bigdigitos[i].modPow(e, n);
           
            
        }
        
        return cifrado;
        
    }
    
    //desciframos con la clave privada
    // d, n
    public String descifrar(BigInteger[] cifrado, BigInteger d,BigInteger n){
        
        BigInteger[] descifrado = new BigInteger[cifrado.length];
        
        //vamos a descifrar con la formula
        // Mens = Cifr^d mod n
        
        for(int j=0;j < descifrado.length;j++){
            
            try{
                descifrado[j] = cifrado[j].modPow(d, n);
            }catch(Exception e){
                System.out.println("Error en la operación: " + e.toString());
            }
            
        }    
        
        char[] charArray = new char[descifrado.length];

        for(int i = 0; i<charArray.length;i++){

            try{
                charArray[i] = (char)(descifrado[i].intValue());
            }catch(Exception e){
                System.out.println("Error en los char: " + e.toString());
            }
            
        }
        String mess = "";
        
        try{
             mess = new String(charArray);
        }catch(Exception e){
            System.out.println("error en el mensaje: " + e.toString());
        }
        return(mess);
        
    }
    
    public static void main(String[] args){
        
        
        Scanner entrada = new Scanner(System.in);
        int tamprimo = 0;
        String mensaje;
        System.out.println("Dame un tamaño de los primos");
        tamprimo = Integer.parseInt(entrada.nextLine());
        
        RSA alicia = new RSA();
        alicia.RSA(tamprimo);
        RSA fernando = new RSA();
        fernando.RSA(tamprimo);
        
        fernando.generarPrimo();
        fernando.generarClaves();
        
        fernando.generarPrimo();
        fernando.generarClaves();
        
        
        System.out.println("Para Alicia, se tiene: ");
        System.out.println("n_a = " + fernando.n);
        System.out.println("e_a = " + fernando.e);
        System.out.println("d_a = " + fernando.d);
        
        System.out.println("Para Fernando, se tiene: ");
        System.out.println("n_f = " + fernando.n);
        System.out.println("e_f = " + fernando.e);
        System.out.println("d_f = " + fernando.d);
        
        System.out.println("Escribe el mensaje que quiere hacer Alicia:");
        mensaje = entrada.nextLine();
        
        
        System.out.println("Alicia cifra el mensaje (N) con la formula: N^(e_f) mod n_f, para cada valor del array de los bytes");
        BigInteger[] cifrado = fernando.cifrar(mensaje, fernando.e, fernando.n);
        for(BigInteger num:cifrado){
            System.out.println(num);
        }
        
        
        System.out.println("Ahora fernando va a descifrarlo, cada valor del arreglo del cifrado (C) que genero Alicia"
                + "Lo descifrara con:  C^(d_f) mod n_f ");
        
        String descifrado = fernando.descifrar(cifrado, fernando.d, fernando.n);
        
        System.out.println("Tu mensaje descifrado es:");
        
        System.out.println(descifrado);
        
        
        
        
        
        
        
        
    }
    
    
    
}
