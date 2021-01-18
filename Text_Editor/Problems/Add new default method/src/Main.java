interface Flying {
    // returns height of flying in meters
    int getHeight();

    default int getHeightInKm() {
        return getHeight() / 1000;
    }
}