class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        if (this.minutes == 59) {
            this.hours = this.hours % 12 + 1;
        }
        this.minutes = (this.minutes + 1) % 60;
    }
}
