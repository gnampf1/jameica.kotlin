package de.gnampf.kotlin;

import de.willuhn.jameica.plugin.AbstractPlugin;

/**
 * Reines Bibliotheks-Plugin: liefert die Kotlin-Runtime (kotlin-stdlib) fuer andere Plugins
 * (via Jameicas gemeinsamen ClassLoader). Enthaelt selbst keine Logik.
 */
public class Plugin extends AbstractPlugin
{
	@Override
	public void init()
	{
	}
}
