package com.exavalu.utils;


import org.apache.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class log4jExample {
    /* Get actual class name to be printed on */
    
   static Logger log = Logger.getLogger(log4jExample.class.getName());
   
   public static void main(String[] args)throws IOException,SQLException{
     
      log.info("Hello this is an info message");
   }
}
