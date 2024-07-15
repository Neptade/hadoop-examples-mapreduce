package com.opstty;

import com.opstty.job.WordCount;
import com.opstty.job.DistrictTrees;
import com.opstty.job.TreeSpecies;
import com.opstty.job.SpeciesCount;
import com.opstty.job.SpeciesMaxHeight;
import com.opstty.job.SortHeight;
import com.opstty.job.OldestTree;
import com.opstty.job.DistrictMostTrees;
import org.apache.hadoop.util.ProgramDriver;

public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("wordcount", WordCount.class,
                    "A map/reduce program that counts the words in the input files.");
            programDriver.addClass("districtCount", DistrictTrees.class, "");
            programDriver.addClass("treeSpecies", TreeSpecies.class, "");
            programDriver.addClass("speciesCount", SpeciesCount.class, "");
            programDriver.addClass("speciesMaxHeight", SpeciesMaxHeight.class, "");
            programDriver.addClass("sortHeight", SortHeight.class, "");
            programDriver.addClass("oldestTree", OldestTree.class, "");
            programDriver.addClass("districtMostTrees", DistrictMostTrees.class, "");

            exitCode = programDriver.run(argv);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.exit(exitCode);
    }
}
