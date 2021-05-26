import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine{

	public static ArrayList<Recepture> FilterRecepies(ArrayList<Recepture> original, String filter) {
		ArrayList<Recepture> filtered = new ArrayList<>();
		Pattern compiledPattern = Pattern.compile(filter);

		for (Recepture i : original) {
			if (i.GetResult() != null) {
				Matcher matcher = compiledPattern.matcher(i.GetResult().GetName());
				if (matcher.find()) {
					filtered.add(i);
				}
			}
		}
		return filtered;
	}

}
