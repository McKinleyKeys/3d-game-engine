

package com.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import org.newdawn.slick.opengl.TextureLoader;


public class ResourceLoader {
    
    public static Texture loadTexture(String fileName) {
        
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];
        
        try {
            
            int id = TextureLoader.getTexture(ext, new FileInputStream(new File("/Users/mckinley/NetBeansProjects/3D Game Engine/res/textures/" + fileName))).getTextureID();
            
            return new Texture(id);
        }
        
        catch (Exception e) {
            
            e.printStackTrace();
            System.exit(1);
        }
        
        return null;
    }
    
    public static String loadShader(String fileName) {
        
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try {
            
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            
            String line;
            
            while ((line = shaderReader.readLine()) != null) {
                
                //System.out.println(line);
                shaderSource.append(line).append("\n");
                
                try {
                    
                    Thread.sleep(10);
                }
                
                catch (Exception e) {
                    
                    e.printStackTrace();
                }
            }
            
            shaderReader.close();
        }
        
        catch (Exception e) {
            
            e.printStackTrace();
            System.exit(1);
        }
        
        return shaderSource.toString();
    }
    
    public static Mesh loadMesh(String fileName) {
        
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];
        
        if (!ext.equals("obj")) {
            
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        BufferedReader meshReader = null;
        
        try {
            
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            
            while ((line = meshReader.readLine()) != null) {
                
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);
                
                if (tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                
                if (tokens[0].equals("v")) {
                    
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3]))));
                }
                
                else if (tokens[0].equals("f")) {
                    
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    
                    if (tokens.length > 4) {
                        
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            
            meshReader.close();
            
            Mesh res = new Mesh();
            
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);
            
            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);
            
            res.addVertices(vertexData, Util.toIntArray(indexData));
            
            return res;
        }
        
        catch (Exception e) {
            
            e.printStackTrace();
            System.exit(1);
        }
        
        return null;
    }
}
