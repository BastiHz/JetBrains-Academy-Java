package editor;

class SearchResult {
    int startIndex;
    int endIndex;

    SearchResult(int index, String foundText) {
        this.startIndex = index;
        this.endIndex = index + foundText.length();
    }
}
