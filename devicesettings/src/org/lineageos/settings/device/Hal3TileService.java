/*
 * Copyright (C) 2021 CarbonROM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package org.lineageos.settings.device;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class Hal3TileService extends TileService {

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        tile.setState(SettingsUtils.cameraHAL3Enable() ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);

        tile.updateTile();
        super.onStartListening();
    }
    
    @Override
    public void onClick() {
        Tile tile = getQsTile();
        boolean enabled = SettingsUtils.cameraHAL3Enable();
        SettingsUtils.writeCameraHAL3Prop(!enabled);
        SettingsUtils.writeCameraRestartProp(this);
        tile.setState(!enabled ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);

        tile.updateTile();
        super.onClick();
    }
}
