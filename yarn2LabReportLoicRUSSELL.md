# 1.8 Remarkable trees of Paris

[loic.russell@bigdata01 ~]$ hdfs dfs -ls\
Found 9 items\
drwx------   - loic.russell loic.russell          0 2024-07-11 20:00 .Trash\
drwx------   - loic.russell loic.russell          0 2024-07-11 15:52 .staging\
drwxr-xr-x   - loic.russell loic.russell          0 2024-07-11 11:52 data\
drwxr-xr-x   - loic.russell loic.russell          0 2024-07-11 14:44 gutenberg\
drwxr-xr-x   - loic.russell loic.russell          0 2024-07-11 14:48 gutenberg-output\
drwxr-xr-x   - loic.russell loic.russell          0 2024-07-11 15:52 gutenberg-output-iterator\
drwxr-xr-x   - loic.russell loic.russell          0 2024-07-11 10:50 raw\
**-rw-r--r--   3 loic.russell loic.russell     182408 2024-07-12 10:10 trees.csv**\
drwxr-xr-x   - loic.russell loic.russell          0 2024-07-11 11:17 wordcount

# 1.8.1 Districts containing trees

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar districtCount trees.csv districtTrees \
...\
INFO mapreduce.Job: Job job_1720701352744_1155 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls districtTrees/\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 19:52 districtTrees/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell         44 2024-07-15 19:52 districtTrees/part-r-00000\
[loic.russell@bigdata01 ~]$ hdfs dfs -head districtTrees/part-r-00000\
11\
12\
13\
14\
15\
16\
17\
18\
19\
20\
3\
4\
5\
6\
7\
8\
9
# 1.8.2 Show all existing species

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar treeSpecies trees.csv treeSpecies\
...\
INFO mapreduce.Job: Job job_1720701352744_1154 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls treeSpecies/\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 19:49 treeSpecies/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell        451 2024-07-15 19:49 treeSpecies/part-r-00000\
[loic.russell@bigdata01 ~]$ hdfs dfs -head treeSpecies/part-r-00000\
araucana\
atlantica\
australis\
baccata\
bignonioides\
biloba\
bungeana\
cappadocicum\
...

# 1.8.3 Number of trees by kinds

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar speciesCount trees.csv speciesCount\
...\
INFO mapreduce.Job: Job job_1720701352744_1007 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls speciesCount\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 15:09 speciesCount/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell        542 2024-07-15 15:09 speciesCount/part-r-00000
[loic.russell@bigdata01 ~]$ hdfs dfs -head speciesCount/part-r-00000\
araucana        1\
atlantica       2\
australis       1\
baccata 2\
bignonioides    1\
biloba  5\
...

# 1.8.4 Maximum height per kind of tree

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar speciesMaxHeight trees.csv speciesMaxHeight\
...\
INFO mapreduce.Job: Job job_1720701352744_1153 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls speciesMaxHeight/\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 19:43 speciesMaxHeight/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell        675 2024-07-15 19:43 speciesMaxHeight/part-r-00000\
[loic.russell@bigdata01 ~]$ hdfs dfs -head speciesMaxHeight/part-r-0000
araucana        9.0\
atlantica       25.0\
australis       16.0\
baccata 13.0\
bignonioides    15.0\
biloba  33.0\
bungeana        10.0\
cappadocicum    16.0\
carpinifolia    30.0\
...

# 1.8.5 Sort the trees height from smallest to largest

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar sortHeight trees.csv sortHeight\
...\
INFO mapreduce.Job: Job job_1720701352744_1070 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls sortHeight/\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 16:19 sortHeight/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell       3426 2024-07-15 16:19 sortHeight/part-r-00000
[loic.russell@bigdata01 ~]$ hdfs dfs -head sortHeight/part-r-00000\
(48.8453050031, 2.35307565328)  2.0\
(48.8320684332, 2.41182825531)  5.0\
(48.867221687, 2.27027693483)   6.0\
(48.8716117578, 2.24933653506)  9.0\
(48.8814628758, 2.38367383179)  10.0\
(48.8471789821, 2.25293802515)  10.0\
(48.8213214388, 2.45537251962)  10.0\
(48.8691485018, 2.27224125103)  10.0\
(48.8615768444, 2.25902702441)  10.0\
(48.8520958913, 2.34740754195)  11.0\
...

# 1.8.6 District containing the oldest tree

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar oldestTree trees.csv oldestTree\
...\
INFO mapreduce.Job: Job job_1720701352744_1156 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls oldestTree/\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 20:00 oldestTree/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell         36 2024-07-15 20:00 oldestTree/part-r-00000
[loic.russell@bigdata01 ~]$ hdfs dfs -head oldestTree/part-r-00000\
5       1601

# 1.8.7 District containing the most trees

[loic.russell@bigdata01 ~]$ yarn jar jarFiles/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar districtMostTrees trees.csv districtMostTrees\
...\
INFO mapreduce.Job: Job job_1720701352744_1150 completed successfully\
...\
INFO mapreduce.Job: Job job_1720701352744_1151 completed successfully\
...\
[loic.russell@bigdata01 ~]$ hdfs dfs -ls districtMostTrees/\
Found 2 items\
-rw-r--r--   3 loic.russell loic.russell          0 2024-07-15 19:27 districtMostTrees/_SUCCESS\
-rw-r--r--   3 loic.russell loic.russell          6 2024-07-15 19:27 districtMostTrees/part-r-00000
[loic.russell@bigdata01 ~]$ hdfs dfs -head districtMostTrees/part-r-00000\
16      36



