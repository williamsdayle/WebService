/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inter;

import javax.xml.ws.Endpoint;

/**
 *
 * @author willi
 */
public class Teste {
    public static void main(String[] args) {
        Endpoint.publish(":http//172.16.105.206:9000/testeOBJ", new Impl());
        
    }
    
}
