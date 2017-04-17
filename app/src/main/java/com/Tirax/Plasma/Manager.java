package com.Tirax.Plasma;

import com.Tirax.Plasma.Options.Auto.Acne.AcneHighOption;
import com.Tirax.Plasma.Options.Auto.Acne.AcneLowOption;
import com.Tirax.Plasma.Options.Auto.Acne.AcneMediumOption;
import com.Tirax.Plasma.Options.Auto.Blepharo.BlepharoHighOption;
import com.Tirax.Plasma.Options.Auto.Blepharo.BlepharoLowOption;
import com.Tirax.Plasma.Options.Auto.Blepharo.BlepharoMediumOption;
import com.Tirax.Plasma.Options.Auto.Mole.MoleHighOption;
import com.Tirax.Plasma.Options.Auto.Mole.MoleLowOption;
import com.Tirax.Plasma.Options.Auto.Mole.MoleMediumOption;
import com.Tirax.Plasma.Options.Auto.Scar.ScarHighOption;
import com.Tirax.Plasma.Options.Auto.Scar.ScarLowOption;
import com.Tirax.Plasma.Options.Auto.Scar.ScarMediumOption;
import com.Tirax.Plasma.Options.ManualOption;
import com.Tirax.Plasma.Storage.Pages;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Manager {
    public static Mode getType() {

        if (Pages.auto_manual == Pages.MANUAL) {

            return new ManualOption();

        } else {
            if (Pages.auto_type == Pages.ACNE) {

                if (Pages.step == Pages.HIGH) {
                    return new AcneHighOption();
                } else if (Pages.step == Pages.MEDIUM) {
                    return new AcneMediumOption();
                } else if (Pages.step == Pages.LOW) {
                    return new AcneLowOption();
                }

            }else if (Pages.auto_type == Pages.BLEPHARO) {

                if (Pages.step == Pages.HIGH) {
                    return new BlepharoHighOption();
                } else if (Pages.step == Pages.MEDIUM) {
                    return new BlepharoMediumOption();
                } else if (Pages.step == Pages.LOW) {
                    return new BlepharoLowOption();
                }

            }else if (Pages.auto_type == Pages.SCAR) {

                if (Pages.step == Pages.HIGH) {
                    return new ScarHighOption();
                } else if (Pages.step == Pages.MEDIUM) {
                    return new ScarMediumOption();
                } else if (Pages.step == Pages.LOW) {
                    return new ScarLowOption();
                }

            }else if (Pages.auto_type == Pages.MOLE) {

                if (Pages.step == Pages.HIGH) {
                    return new MoleHighOption();
                } else if (Pages.step == Pages.MEDIUM) {
                    return new MoleMediumOption();
                } else if (Pages.step == Pages.LOW) {
                    return new MoleLowOption();
                }

            }
            return null;
        }

    }




};

