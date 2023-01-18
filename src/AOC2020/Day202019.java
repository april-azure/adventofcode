package AOC2020;

import Helper.Utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Day202019 {

    Map<Integer, Rule> ruleMap = new HashMap<>();
    Map<Integer, Set<Integer>> dependencyCount = new HashMap<>();
    Map<Integer, List<Integer>> dependencyMap = new HashMap<>();

    int maxLen = 10;

    public class Rule {
        int index;
        List<List<Integer>> rules;
        Set<String> options = new HashSet<>();

        public Rule(int index) {
            this.index = index;
        }

        public void setOption(String val) {
            options.add(val);
        }

        public void setRules(List<List<Integer>> list) {
            rules = list;
        }

        public List<Integer> getAllDependency() {
            return rules.stream().flatMap(Collection::stream).toList();
        }

        public Set<String> getOptions() {
            System.out.println("getoptions " + index);
            if (options.size() > 0) {
                return options;
            }
            Set<String> alloptions = new HashSet<>();
            for (List<Integer> rule : rules) {
                if (!ruleHasCycle(rule)) {
                    Set<String> temp = new HashSet<>();
                    for (int subRule : rule) {
                        Set<String> curOptions = ruleMap.get(subRule).getOptions();
                        temp = mergeOptions(temp, curOptions);
                    }
                    alloptions.addAll(temp);
                } else {
                    Set<String> temp = findOptionsForCycle(rule, alloptions);
                    alloptions.addAll(temp);
                }

            }
            options = alloptions;
            return options;
        }

        private Set<String> findOptionsForCycle(List<Integer> rule, Set<String> foundOptions) {
            Set<String> tmp = new HashSet<>();
            for (int val : rule) {
                if (val != this.index) {
                    Set<String> otherOptions = ruleMap.get(val).getOptions();
                    tmp = mergeOptions(tmp, otherOptions);
                } else {
                    tmp = mergeOptions(tmp, foundOptions);
                }
            }
            foundOptions.addAll(tmp);
            return foundOptions;
        }

        private boolean ruleHasCycle(List<Integer> rule) {
            Set<Integer> set = new HashSet<>(rule);
            return set.contains(this.index);
        }
    }


    private Set<String> mergeOptions(Set<String> op1, Set<String> op2) {
        if (op1.size() == 0) return op2;
        if (op2.size() == 0) return op1;
        Set<String> res = new HashSet<>();
        for (String str1 : op1) {
            for (String str2 : op2) {
                res.add(str1 + str2);
            }
        }
        return res;
    }

    public void solve(List<String> lines) {
        int seperationIndex = Utils.getLineIndexFor(lines, "");
        Queue<Integer> processIndexQueue = new LinkedList<>();
        for (int i = 0; i < seperationIndex; i++) {
            int ruleNo = Integer.parseInt(lines.get(i).split(":")[0]);
            String ruleString = lines.get(i).split(":")[1].trim();
            Rule rule = new Rule(ruleNo);
            dependencyCount.put(ruleNo, new HashSet<>());
            if (ruleString.startsWith("\"")) {
                rule.setOption(ruleString.replace("\"", ""));
                processIndexQueue.offer(ruleNo);
            } else {
                List<List<Integer>> formatedRules = formatSubrules(ruleString);
                rule.setRules(formatedRules);

                for (int dep : rule.getAllDependency()) {
                    if (!dependencyMap.containsKey(dep)) {
                        dependencyMap.put(dep, new ArrayList<>());
                    }
                    dependencyMap.get(dep).add(ruleNo);
                    dependencyCount.get(ruleNo).add(dep);
                }
            }

            ruleMap.put(ruleNo, rule);
        }

        generateAllOptionsForRule0(processIndexQueue);
        Set<String> rule0 = ruleMap.get(0).getOptions();

        int matchCnt = 0;
        for (int i = seperationIndex + 1; i < lines.size(); i++) {
            if (rule0.contains(lines.get(i))) {
                matchCnt++;
            }
        }

        System.out.println("match count " + matchCnt);
    }

    public void generateAllOptionsForRule0(Queue<Integer> q) {
        while (q.size() > 0) {
            int ruleNo = q.poll();
//            System.out.println("process rule No " + ruleNo);
            Rule rule = ruleMap.get(ruleNo);
            rule.getOptions();
            // reduce dependency

            for (int dep : dependencyMap.getOrDefault(ruleNo, List.of())) {
                dependencyCount.get(dep).remove(ruleNo);
                if (dependencyCount.get(dep).size() == 0) {
                    q.offer(dep);
                }
            }
        }
    }

    public List<List<Integer>> formatSubrules(String str) {
        String[] parts = str.trim().split("\\|");
        List<List<Integer>> list = new ArrayList<>();
        for (String part : parts) {
            List<Integer> rule = new ArrayList<>();
            String[] rules = part.trim().split(" ");
            for (String r : rules) {
                rule.add(Integer.parseInt(r));
            }
            list.add(rule);
        }
        return list;
    }
}
