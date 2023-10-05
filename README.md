# survival iets ding

- evolutie
  = speler bepaald (andere wezens doen zelf)
  - pairen met andere wezens -> beïnvloedt permanente eigenschappen
  - voeding enz. -> tijdelijke eigenschap
- omgeving
  = computer bepaald
  - invloed (temp, nat, enz.)
- doel
  = survival
  - omstandigheden steeds moeilijker
  - als enige overblijven

- World
  - Creatures
    - Codes (DNA) -> PropContainer
      - Speed, damage, max energy, resistance, enz.. -> Code -> Prop
      - Changed by pairing
    - Stats -> PropContainer
      - Energy -> Stat -> Prop
        - Changed by actions (based on env)
    - Actions
      - Eat, Pair -> Action
  - Environment
    - Temp, weather, enz..
