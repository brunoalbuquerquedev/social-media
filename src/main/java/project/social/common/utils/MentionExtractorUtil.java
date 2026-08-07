package project.social.common.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MentionExtractorUtil {

    private static final Pattern MENTION_PATTERN = Pattern.compile("@([a-zA-Z0-9_]+)");

    /**
     * Extracts all user mentions from content.
     *
     * @param content the content to extract mentions from
     * @return a set of mentioned usernames (without @ symbol)
     */
    public static Set<String> extractMentions(String content) {
        Set<String> mentions = new HashSet<>();

        if (content == null || content.isEmpty()) {
            return mentions;
        }

        Matcher matcher = MENTION_PATTERN.matcher(content);
        while (matcher.find()) {
            mentions.add(matcher.group(1));
        }

        return mentions;
    }

    /**
     * Checks if content contains any mentions.
     *
     * @param content the content to check
     * @return true if content contains mentions, false otherwise
     */
    public static boolean hasMentions(String content) {
        if (content == null || content.isEmpty()) {
            return false;
        }
        return MENTION_PATTERN.matcher(content).find();
    }

    /**
     * Replaces mentions with a formatted string.
     *
     * @param content the content with mentions
     * @param format the format string with {mention} placeholder
     * @return the content with mentions replaced
     */
    public static String replaceMentions(String content, String format) {
        if (content == null || content.isEmpty()) {
            return content;
        }

        return MENTION_PATTERN.matcher(content)
                .replaceAll(mention -> String.format(format, mention.group(1)));
    }
}
