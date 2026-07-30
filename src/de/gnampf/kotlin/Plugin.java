package de.gnampf.kotlin;

import java.io.File;

import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.logging.Logger;

/**
 * Reines Bibliotheks-Plugin. Prueft beim Start, ob wirklich die von diesem Plugin
 * mitgelieferte kotlin-stdlib-Version geladen wurde und nicht eine abweichende Kopie aus einem
 * anderen Plugin (die im gemeinsamen ClassLoader "gewinnen" koennte).
 */
public class Plugin extends AbstractPlugin
{
	@Override
	public void init()
	{
		checkLibrary(kotlin.Unit.class, "kotlin-stdlib");
	}

	private void checkLibrary(Class<?> clazz, String libName)
	{
		try
		{
			var src = clazz.getProtectionDomain().getCodeSource();
			if (src == null || src.getLocation() == null)
				return;
			File loaded = new File(src.getLocation().toURI()).getCanonicalFile();
			File myDir  = new File(getManifest().getPluginDir()).getCanonicalFile();
			if (!loaded.getPath().startsWith(myDir.getPath() + File.separator))
			{
				Logger.error("Plugin '" + getManifest().getName() + "': Es wird eine ANDERE " + libName
					+ "-Version geladen als die von diesem Plugin mitgelieferte. Geladen aus: "
					+ loaded.getAbsolutePath()
					+ " - bitte diese abweichende Datei loeschen, damit die vom Plugin gelieferte Version genutzt wird.");
			}
		}
		catch (Exception e)
		{
			Logger.warn("Plugin '" + getManifest().getName() + "': Versionspruefung fuer " + libName
				+ " fehlgeschlagen: " + e);
		}
	}
}
