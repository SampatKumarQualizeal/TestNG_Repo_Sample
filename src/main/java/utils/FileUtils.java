package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    private static final Logger logger = LogManager.getLogger(FileUtils.class);

    public static boolean fileExists(String filePath) {
        logger.debug("Checking if file exists: {}", filePath);
        Path path = Paths.get(filePath);
        boolean exists = Files.exists(path) && Files.isRegularFile(path);
        logger.debug("File exists: {}", exists);
        return exists;
    }

    public static boolean directoryExists(String dirPath) {
        logger.debug("Checking if directory exists: {}", dirPath);
        Path path = Paths.get(dirPath);
        boolean exists = Files.exists(path) && Files.isDirectory(path);
        logger.debug("Directory exists: {}", exists);
        return exists;
    }

    public static void createDirectory(String dirPath) {
        logger.debug("Creating directory: {}", dirPath);
        try {
            Files.createDirectories(Paths.get(dirPath));
            logger.info("Directory created: {}", dirPath);
        } catch (IOException e) {
            logger.error("Failed to create directory: {}", dirPath, e);
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String filePath) {
        logger.debug("Reading file: {}", filePath);
        Path path = Paths.get(filePath);
        try {
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, StandardCharsets.UTF_8);
            logger.info("File read successfully: {}", filePath);
            return content;
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String filePath, String content) {
        logger.debug("Writing file: {}", filePath);
        try {
            createDirectory(getDirectoryName(filePath));
            Path path = Paths.get(filePath);
            Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            logger.info("File written successfully: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to write file: {}", filePath, e);
            throw new RuntimeException(e);
        }
    }

    public static void appendFile(String filePath, String content) {
        logger.debug("Appending to file: {}", filePath);
        try {
            createDirectory(getDirectoryName(filePath));
            Path path = Paths.get(filePath);
            Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            logger.info("Content appended to file: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to append to file: {}", filePath, e);
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String filePath) {
        logger.debug("Deleting file: {}", filePath);
        try {
            Files.deleteIfExists(Paths.get(filePath));
            logger.info("File deleted: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to delete file: {}", filePath, e);
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectory(String dirPath, boolean recursive) {
        logger.debug("Deleting directory: {}", dirPath);
        Path dir = Paths.get(dirPath);
        try {
            if (recursive && Files.exists(dir)) {
                List<Path> pathsToDelete;
                try (Stream<Path> walk = Files.walk(dir)) {
                    pathsToDelete = walk.sorted((a, b) -> b.compareTo(a))
                            .collect(Collectors.toList());
                }
                for (Path path : pathsToDelete) {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        logger.error("Failed to delete: {}", path, e);
                    }
                }
            } else {
                Files.deleteIfExists(dir);
            }
            logger.info("Directory deleted: {}", dirPath);
        } catch (IOException e) {
            logger.error("Failed to delete directory: {}", dirPath, e);
            throw new RuntimeException(e);
        }
    }

    public static void copyFile(String sourcePath, String destinationPath) {
        logger.debug("Copying file: {} -> {}", sourcePath, destinationPath);
        try {
            createDirectory(getDirectoryName(destinationPath));
            Files.copy(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
            logger.info("File copied: {} -> {}", sourcePath, destinationPath);
        } catch (IOException e) {
            logger.error("Failed to copy file: {} -> {}", sourcePath, destinationPath, e);
            throw new RuntimeException(e);
        }
    }

    public static void moveFile(String sourcePath, String destinationPath) {
        logger.debug("Moving file: {} -> {}", sourcePath, destinationPath);
        try {
            createDirectory(getDirectoryName(destinationPath));
            Files.move(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
            logger.info("File moved: {} -> {}", sourcePath, destinationPath);
        } catch (IOException e) {
            logger.error("Failed to move file: {} -> {}", sourcePath, destinationPath, e);
            throw new RuntimeException(e);
        }
    }

    public static String getFileName(String filePath) {
        logger.debug("Getting file name: {}", filePath);
        String name = Paths.get(filePath).getFileName().toString();
        logger.info("File name: {}", name);
        return name;
    }

    public static String getDirectoryName(String filePath) {
        logger.debug("Getting directory name: {}", filePath);
        Path parent = Paths.get(filePath).getParent();
        String dirName = (parent != null) ? parent.toString() : "";
        logger.info("Directory name: {}", dirName);
        return dirName;
    }
}
