package org.nivla.mediac.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MediaController {

    private String imgPath="E:\\FILES\\appleIV";
    
    @GetMapping(path = "/")
    public String index() {

        //processDirectory( "E:\\FILES\\appleIV" );
try {

    System.out.println("======== ST 1 ===========");

Thumbnails.of(new File(imgPath).listFiles())
    .size(160, 120)
    .outputFormat("jpg")
    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);

    System.out.println("======== ST 2 ===========");
}
catch(IOException ex ) { System.out.println(ex.getMessage());  }

        return "index";
    }

    @GetMapping(path = "/photo")
    public String photoList(Principal principal, Model model ) {
  


        if (  !Files.exists(Paths.get(imgPath+"\\.tdb")) ) 
        {
            Files.createDirectory( Paths.get(imgPath+"\\.tdb"), null);
        }
        ;

/*        
        List<String> fileList = Stream.of(new File(imgPath).listFiles())
            .filter(file -> !file.isDirectory()  )
            .map(File::getName)
            .collect(Collectors.toList() )
        ;
*/
        List<String> fileList = Stream.of(new File(imgPath).listFiles())
        .filter(file -> !file.isDirectory()  )
        .map(file. )
        .collect(Collectors.toList() )
    ;



        model.addAttribute("images", fileList);
        
//        model.addAttribute("hostname", "r5600g" );

        try {
            model.addAttribute("hostname",   InetAddress.getLocalHost().getHostName() );
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("======== XXXXXX ===========");
        System.out.println(System.getenv("HOSTNAME"));
        System.out.println("======== YYYYYYY ===========");
/*
        try {

            System.out.println("======== ZST 1 ===========");

        Thumbnails.of(new File("E:\\FILES\\appleIV").listFiles())
            .size(160, 120)
            .outputFormat("jpg")
            .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
                
            System.out.println("======== ZST 2 ===========");
        }

        catch(IOException ex ) { 
            System.out.println("======== EXXXXXX ===========");
            System.out.println(ex.getMessage());  
        }

*/

        return "photo" ;
    }

    public String processDirectory ( String path)
    {

 List<String> fileList = Stream.of(new File(path).listFiles())
.filter(file -> !file.isDirectory())
.map(File::getName)
//        .forEach( x -> System.out.println(x) );
.collect(Collectors.toList() )
        ;

        return "";
    }

        
}
