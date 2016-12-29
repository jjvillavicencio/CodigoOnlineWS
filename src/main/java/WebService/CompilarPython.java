/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jjvillavicencio
 */
@WebService(serviceName = "CompilarPython")
public class CompilarPython {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "JavaRunCommand")
    public String JavaRunCommand(@WebParam(name = "codigo") String codigo, @WebParam(name = "idUser") String idUser) throws IOException {
        
        String ruta = "/home/"+idUser+"-codigo.py";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("#El fichero de texto ya estaba creado.\n"+ codigo);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("#Acabo de crear el fichero de texto.\n"+ codigo);
        }
        bw.close();
        
        String s = "";
        try {
            Process p = Runtime.getRuntime().exec("python /home/"+idUser+"-codigo.py");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
//            //Leer la salida de la ejecucion del comando
//            //System.out.println("Esta es la salida del comando:\n");
            //while((stdInput.readLine()) != null){
              //  s = s + stdInput.readLine().toString() + "\n";
            //}
//            
//            //Errores en la ejecución del comando
//            //System.out.println("Estos son los errores que se presentaron:\n");
//            while((s = stdError.readLine()) != null){
//                s = s+s;
//            }

        return stdInput.readLine().toString();
        //return s;
       } catch (IOException ex) {
            Logger.getLogger(CompilarPython.class.getName()).log(Level.SEVERE, null, ex);
        return "hola2";
        }
    }
}
