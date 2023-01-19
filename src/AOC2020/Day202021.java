package AOC2020;

import java.util.*;

public class Day202021 {

    Set<String> allergens = new HashSet<>();
    List<Set<String>> dishes = new ArrayList<>();
    Map<String, List<Integer>> allergensToDishMap = new HashMap<>();

    Map<String, String> allergensToIngredient = new HashMap<>();

    private boolean dfs() {
        if (allergens.size() == allergensToIngredient.size()) {
            return true;
        }

        String allergen = nextAllergen();
        List<Set<String>> dishes = dishesContainsAllergen(allergen);
        List<String> possibleIngredients = findPossibleIngredients(dishes);

        for (String ingredient : possibleIngredients) {
            allergensToIngredient.put(allergen, ingredient);
            if (dfs()) {
                return true;
            }
            allergensToIngredient.remove(allergen);
        }
        return false;
    }

    private List<String> findPossibleIngredients(List<Set<String>> dishes) {
        List<String> res = new ArrayList<>();
        for (String ingredient : dishes.get(0)) {
            if (allergensToIngredient.values().contains(ingredient)) {
                continue;
            }

            boolean isValid = true;
            for (Set<String> dish : dishes) {
                if (!dish.contains(ingredient)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                res.add(ingredient);
            }
        }
        return res;
    }

    private List<Set<String>> dishesContainsAllergen(String allergen) {
        List<Integer> dishesIndex = allergensToDishMap.get(allergen);
        List<Set<String>> tmp = new ArrayList<>();
        for (int index : dishesIndex) {
            tmp.add(dishes.get(index));
        }
        return tmp;
    }

    private String nextAllergen() {
        for (String allergen : allergens) {
            if (!allergensToIngredient.keySet().contains(allergen)) {
                return allergen;
            }
        }
        System.out.println("wrong : nextAllergen found empty");
        return "";
    }

    public void solve(List<String> lines) {
        int i = 0;
        for (String line : lines) {
            String[] pts = line.split("\\(contains");
            String[] ingredients = pts[0].trim().split(" ");
            Set<String> dish = new HashSet<>();
            dish.addAll(Arrays.asList(ingredients));
            dishes.add(dish);

            String[] allergenIngredients = pts[1].trim()
                    .replace(")", "")
                    .replace(" ", "")
                    .split(",");
            allergens.addAll(Arrays.asList(allergenIngredients));

            for (String allergen : allergenIngredients) {
                if (!allergensToDishMap.containsKey(allergen)) {
                    allergensToDishMap.put(allergen, new ArrayList<>());
                }
                allergensToDishMap.get(allergen).add(i);
            }

            i++;
        }

        dfs();

        int cnt = count();
        findCanonicalList();
    }

    private String findCanonicalList() {
        List<String> sortedAllergens = new ArrayList<>(allergens);
        sortedAllergens.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        for (String allergen : sortedAllergens) {
            sb.append(allergensToIngredient.get(allergen));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private int count() {
        int cnt = 0;
        for (Set<String> dish : dishes) {
            for (String ingredient : dish) {
                if (!allergensToIngredient.values().contains(ingredient)) {
                    cnt++;
                }
            }
        }
        System.out.println("Count " + cnt);
        return cnt;
    }
}
