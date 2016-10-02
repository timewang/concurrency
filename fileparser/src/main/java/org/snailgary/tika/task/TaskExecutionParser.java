/**
 *
 */
package org.snailgary.tika.task;

import org.snailgary.tika.TikaParser;

import java.io.File;
import java.util.List;
import java.util.concurrent.*;

/**
 * ***********************************************************
 *
 * @类名 ：TaskExecutionParser.java
 * @DESCRIPTION :
 * @AUTHOR : wangzhongfu
 * @DATE : 2016/10/2
 * ***********************************************************
 */
public class TaskExecutionParser {

    private final ExecutorService executor ;

    public TaskExecutionParser(ExecutorService executor) {
        this.executor = executor;
    }

    public void parseFiles(final List<File> files) throws ExecutionException, InterruptedException {

        CompletionService<String> completionService = new ExecutorCompletionService<String>(this.executor);
        for(final File file : files){
            completionService.submit(() -> TikaParser.parseFileToString(file));
        }
        for(int i = 0 , n = files.size() ; i < n ; i++){
            File file = files.get(i);
            Future<String> future = completionService.take();
            this.handleParseReult(file.getName(),future.get());
            //stringStringMap.put(file.getName() + System.currentTimeMillis(),future.get());
        }

    }

    protected void handleParseReult(String fileName,String fileContent){

    }
}
