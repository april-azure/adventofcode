package Y2022;

import java.util.*;

public class Day7 {

    class Entry {
        public boolean isFile = true;
        public String name;
        public int size = -1; // only for file
        public Map<String, Entry> childMap;
        public Entry parent = null;

        public Entry(String name, int size, Entry parent) {
            this.name = name;
            this.isFile = true;
            this.size = size;
            childMap = null;
            this.parent = parent;
        }

        public Entry(String name, Entry parent) {
            this.isFile = false;
            this.name = name;
            this.parent = parent;
            childMap = new HashMap<>();
        }

        public void addEntry(Entry entry) {
            this.childMap.put(entry.name, entry);
        }

        public int getSize() {
            if (isFile) {
                return size;
            }
            if (size != -1) {
                return size;
            }
            int sum = 0;
            for (Entry entry : childMap.values()) {
                sum += entry.getSize();
            }
            size = sum;
            return sum;
        }
    }
    int totalSize = 0;
    public void findTotalSizeSmallerThan10000() {
        Entry root = new Entry("root", null);
        Entry cur = root;
        // establish the directory tree
        readInput(root);
        // findTotalSizeSmallerThan10000(root);
        int rootSize = root.getSize();
        int freeSpace = 70000000 - rootSize;
        int targetSize = 30000000 - freeSpace;
        Entry smallestEntry = findSmallestDirectoryToDelete(root, null, targetSize);
        System.out.println(smallestEntry.name + " " + smallestEntry.getSize());
    }

    private void findTotalSizeSmallerThan10000(Entry entry) {
        if (!entry.isFile) {
            int size = entry.getSize();
            if (size < 100000) {
                totalSize += size;
            }
            for (Entry dir : entry.childMap.values()) {
                findTotalSizeSmallerThan10000(dir);
            }
        }
    }

    private Entry findSmallestDirectoryToDelete(Entry entry, Entry smallestEntry, int targetSize) {
        if (!entry.isFile) {
            for (Entry childDirectory : entry.childMap.values()) {
                smallestEntry = findSmallestDirectoryToDelete(childDirectory, smallestEntry, targetSize);
            }

            int size = entry.getSize();
            if (size > targetSize) {
                if (smallestEntry == null) {
                    smallestEntry = entry;
                }
                if (size < smallestEntry.getSize()) {
                    smallestEntry = entry;
                }
            }
        }

        return smallestEntry;
    }

    private void readInput(Entry root) {
        Scanner in = new Scanner(System.in);
        Entry cur = root;
        while (in.hasNext()) {
            String line = in.nextLine();
            String cmd = line;
            if (line.startsWith("$")) {
                cmd = line.substring(2);
            }
            cur = parseCmd(root, cur, cmd);
        }
    }

    // return the current directory after the cmd is executed
    private Entry parseCmd(Entry root, Entry curDir, String cmd) {
        if (cmd.startsWith("cd")) {
            String dirName = cmd.substring(3);
            if (dirName.equals("/")) {
                return root;
            }

            if (dirName.equals("..")) {
                return curDir.parent;
            }

            return curDir.childMap.get(dirName);
        }

        if (cmd.startsWith("ls")) {
            return curDir;
        }

        // dir
        if (cmd.startsWith("dir")) {
            String dirName = cmd.substring(4);
            Entry newDir = new Entry(dirName, curDir);
            curDir.addEntry(newDir);
            return curDir;
        }

        // file with size
        String[] fileWithSize= cmd.split(" ");
        int size = Integer.parseInt(fileWithSize[0]);
        String fileName = fileWithSize[1];
        Entry newFile = new Entry(fileName, size, curDir);
        curDir.addEntry(newFile);

        return curDir;
    }
}
