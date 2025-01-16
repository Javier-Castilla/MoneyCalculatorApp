package software.ulpgc.moneycalculator.architecture.view;

public interface SettingDisplay {
    SettingDisplay put(int settingPosition, String settingString);
    String get(int settingPosition);
}
