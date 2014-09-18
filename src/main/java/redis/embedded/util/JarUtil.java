package redis.embedded.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

import redis.embedded.RedisServer;

public class JarUtil {

    public static File extractExecutableFromJar(String executable) throws IOException {
        
    	File tmpDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
        tmpDir.deleteOnExit();
        tmpDir.mkdirs();

        InputStream redisExecutableIs = RedisServer.class.getClassLoader().getResourceAsStream(executable);
        File redisExecutableFile = new File(tmpDir, executable);
        
        long num = Files.copy(redisExecutableIs, redisExecutableFile.getAbsoluteFile().toPath());
        
        redisExecutableFile.setExecutable(true);
        redisExecutableFile.deleteOnExit();
    	
    	return redisExecutableFile;
    }
}
