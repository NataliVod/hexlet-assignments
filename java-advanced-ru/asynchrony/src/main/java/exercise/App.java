package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String resultPath) {
        Path normalizedPath1 = Paths.get(path1).toAbsolutePath().normalize();
        Path normalizedPath2 = Paths.get(path2).toAbsolutePath().normalize();
        Path normalizedResultPath = Paths.get(resultPath).toAbsolutePath().normalize();

        CompletableFuture<byte[]> data1 = CompletableFuture.supplyAsync(() -> {
            byte[] result;
            try {
                result = Files.readAllBytes(normalizedPath1);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return result;
        });
        CompletableFuture<byte[]> data2 = CompletableFuture.supplyAsync(() -> {
            byte[] result;
            try {
                result = Files.readAllBytes(normalizedPath2);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
                return result;
        });

        return data1.thenCombine(data2, (bytes1, bytes2) -> {
            try {
                Files.write(normalizedResultPath, bytes1, StandardOpenOption.CREATE);
                Files.write(normalizedResultPath, bytes2, StandardOpenOption.APPEND);
                return "Successfully combined and written to file";
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<Long> getDirectorySize(String path) {

        return CompletableFuture.supplyAsync(() -> {
            Long size;
            try {
                size = Files.walk(getFullPath(path), 1)
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> {
                            try {
                                return Files.size(p);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return size;

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("file1.txt", "file2.txt", "dest.txt");
        result.get();
        // END
    }
}

